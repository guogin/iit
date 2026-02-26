package com.yahaha.iit.calc;

import com.yahaha.iit.util.I18nUtil;
import com.yahaha.iit.util.MoneyUtil;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AnnualComprehensiveIncomeAssessor implements TaxableIncomeAssessor {
    @Override
    public TraceableTaxBaseAmount determineTaxableAmount(TaxCalculationParameter parameter, Locale locale) {
        //费用
        MonetaryAmount expenses = parameter.getServiceRemuneration()
                .add(parameter.getAuthorsRemuneration())
                .add(parameter.getRoyaltyFees())
                .multiply(0.2); // 20%

        //稿酬所得免税部分
        MonetaryAmount exemptionFromAuthorsRemuneration = parameter.getAuthorsRemuneration()
                .multiply(0.8)  // 1 - 20%
                .multiply(0.3);  // 30%

        // 减除费用
        MonetaryAmount deductibleExpenses = MoneyUtil.toAmount(60000);

        Map<String, MonetaryAmount> additions = new LinkedHashMap<>();
        Map<String, MonetaryAmount> deductions = new LinkedHashMap<>();

        additions.put(I18nUtil.getMessage("annual.comprehensive.income", locale), parameter.getAnnualWageIncome());
        additions.put(I18nUtil.getMessage("service.remuneration", locale), parameter.getServiceRemuneration());
        additions.put(I18nUtil.getMessage("authors.remuneration", locale), parameter.getAuthorsRemuneration());
        additions.put(I18nUtil.getMessage("royalty.fees", locale), parameter.getRoyaltyFees());

        if (parameter.getBonusTaxationOption() == BonusTaxationOption.INTEGRATED_TAXATION) {
            additions.put(I18nUtil.getMessage("annual.one.time.bonus", locale), parameter.getAnnualOneTimeBonus());
        }

        deductions.put(I18nUtil.getMessage("expenses", locale), expenses);
        deductions.put(I18nUtil.getMessage("authors.remuneration.exemption", locale), exemptionFromAuthorsRemuneration);
        deductions.put(I18nUtil.getMessage("deductible.expenses", locale), deductibleExpenses);
        deductions.put(I18nUtil.getMessage("additional.special.deductions", locale), parameter.getAdditionalSpecialDeductions());
        deductions.put(I18nUtil.getMessage("other.deductions", locale), parameter.getOtherDeductions());

        List<TraceItem> detailItems = new ArrayList<>();

        additions.forEach((k, v) -> detailItems.add(new TraceItem(k, v)));
        deductions.forEach((k, v) -> detailItems.add(new TraceItem(k, v.negate())));

        MonetaryAmount taxableAnnualComprehensiveIncome = detailItems.stream()
            .map(TraceItem::getAmount)
                .reduce(MoneyUtil.ZERO, MonetaryAmount::add);
        if (taxableAnnualComprehensiveIncome.isLessThan(MoneyUtil.ZERO)) {
            taxableAnnualComprehensiveIncome = MoneyUtil.ZERO;
        }

        TraceLog traceLog = buildTraceLog(additions, deductions, taxableAnnualComprehensiveIncome, locale);

        return new TraceableTaxBaseAmount(taxableAnnualComprehensiveIncome, traceLog);
    }

    private TraceLog buildTraceLog(Map<String, MonetaryAmount> additions,
                                   Map<String, MonetaryAmount> deductions,
                                   MonetaryAmount taxableAnnualComprehensiveIncome,
                                   Locale locale) {
        List<TraceItem> diagnostics = new ArrayList<>();
        if (!additions.isEmpty()) {
            for (String k : additions.keySet()) {
                MonetaryAmount v = additions.get(k);
                if (!v.isZero()) {
                    diagnostics.add(new TraceItem(k, v));
                }
            }
        }
        if (!deductions.isEmpty()) {
            for (String k : deductions.keySet()) {
                MonetaryAmount v = deductions.get(k);
                if (!v.isZero()) {
                    diagnostics.add(new TraceItem(I18nUtil.getMessage("deduction.prefix", locale) + k, v.negate()));
                }
            }
        }

        TraceLog traceLog = TraceLog.builder()
                .headerMessage(new DiagnosticMessage(I18nUtil.getMessage("annual.comprehensive.income.header", locale)))
                .body(diagnostics)
                .footerMessage(new DiagnosticMessage(I18nUtil.getMessage("annual.comprehensive.income.footer", locale), MoneyUtil.format(taxableAnnualComprehensiveIncome)))
                .build();

        return traceLog;
    }
}
