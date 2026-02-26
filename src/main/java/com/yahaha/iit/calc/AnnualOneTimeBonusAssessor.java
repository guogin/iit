package com.yahaha.iit.calc;

import com.yahaha.iit.util.I18nUtil;
import com.yahaha.iit.util.MoneyUtil;

import javax.money.MonetaryAmount;
import java.util.Locale;

public class AnnualOneTimeBonusAssessor implements TaxableIncomeAssessor {
    @Override
    public TraceableTaxBaseAmount determineTaxableAmount(TaxCalculationParameter parameter, Locale locale) {
        MonetaryAmount taxableAnnualOneTimeBonus = parameter.getAnnualOneTimeBonus();
        TraceLog traceLog = TraceLog.builder()
            .headerMessage(new DiagnosticMessage(I18nUtil.getMessage("annual.one.time.bonus.header", locale)))
            .footerMessage(new DiagnosticMessage(I18nUtil.getMessage("annual.one.time.bonus.footer", locale), MoneyUtil.format(taxableAnnualOneTimeBonus)))
            .build();
        return new TraceableTaxBaseAmount(taxableAnnualOneTimeBonus, traceLog);
    }
}
