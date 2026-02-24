package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AnnualComprehensiveIncomeAssessor implements TaxableIncomeAssessor {
    @Override
    public TraceableTaxBaseAmount determineTaxableAmount(TaxCalculationParameter request) {
        //费用
        MonetaryAmount expenses = request.getServiceRemuneration()
                .add(request.getAuthorsRemuneration())
                .add(request.getRoyaltyFees())
                .multiply(0.2); // 20%

        //稿酬所得免税部分
        MonetaryAmount exemptionFromAuthorsRemuneration = request.getAuthorsRemuneration()
                .multiply(0.8)  // 1 - 20%
                .multiply(0.3);  // 30%

        // 减除费用
        MonetaryAmount deductibleExpenses = MoneyUtil.toAmount(60000);

        Map<String, MonetaryAmount> additions = new LinkedHashMap<>();
        Map<String, MonetaryAmount> deductions = new LinkedHashMap<>();

        additions.put("年度工资收入", request.getAnnualWageIncome());
        additions.put("劳务报酬", request.getServiceRemuneration());
        additions.put("稿酬", request.getAuthorsRemuneration());
        additions.put("特许权使用费", request.getRoyaltyFees());

        if (request.getBonusTaxationOption() == BonusTaxationOption.INTEGRATED_TAXATION) {
            additions.put("全年一次性奖金", request.getAnnualOneTimeBonus());
        }

        deductions.put("费用", expenses);
        deductions.put("稿酬所得免税部分", exemptionFromAuthorsRemuneration);
        deductions.put("减除费用", deductibleExpenses);
        deductions.put("专项附加扣除", request.getAdditionalSpecialDeductions());
        deductions.put("其他扣除项目", request.getOtherDeductions());

        List<TraceItem> detailItems = new ArrayList<>();

        additions.forEach((k, v) -> detailItems.add(new TraceItem(k, v)));
        deductions.forEach((k, v) -> detailItems.add(new TraceItem(k, v.negate())));

        MonetaryAmount taxableAnnualComprehensiveIncome = detailItems.stream()
            .map(TraceItem::getAmount)
                .reduce(MoneyUtil.ZERO, MonetaryAmount::add);
        if (taxableAnnualComprehensiveIncome.isLessThan(MoneyUtil.ZERO)) {
            taxableAnnualComprehensiveIncome = MoneyUtil.ZERO;
        }

        TraceLog traceLog = buildTraceLog(additions, deductions, taxableAnnualComprehensiveIncome);

        return new TraceableTaxBaseAmount(taxableAnnualComprehensiveIncome, traceLog);
    }

    private TraceLog buildTraceLog(Map<String, MonetaryAmount> additions,
                                   Map<String, MonetaryAmount> deductions,
                                   MonetaryAmount taxableAnnualComprehensiveIncome) {
        List<TraceItem> diagnostics = new ArrayList<>();
        additions.forEach((k, v) -> {
            if (!v.isZero()) {
                diagnostics.add(new TraceItem(k, v));
            }
        });
        deductions.forEach((k, v) -> {
            if (!v.isZero()) {
                diagnostics.add(new TraceItem("扣除：" + k, v.negate()));
            }
        });

        TraceLog traceLog = TraceLog.builder()
                .headerMessage(new DiagnosticMessage("计算全年应纳税综合所得额"))
                .body(diagnostics)
                .footerMessage(new DiagnosticMessage("全年应纳税综合所得额: {0}", MoneyUtil.format(taxableAnnualComprehensiveIncome)))
                .build();

        return traceLog;
    }
}
