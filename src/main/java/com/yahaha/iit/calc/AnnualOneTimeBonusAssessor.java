package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;
import com.yahaha.iit.util.I18nUtil;
import java.util.Locale;

import javax.money.MonetaryAmount;

public class AnnualOneTimeBonusAssessor implements TaxableIncomeAssessor {
    @Override
    public TraceableTaxBaseAmount determineTaxableAmount(TaxCalculationParameter request) {
        MonetaryAmount taxableAnnualOneTimeBonus = request.getAnnualOneTimeBonus();
        Locale locale = request.getLocale();
        TraceLog traceLog = TraceLog.builder()
            .headerMessage(new DiagnosticMessage(I18nUtil.getMessage("annual.one.time.bonus.header", locale)))
            .footerMessage(new DiagnosticMessage(I18nUtil.getMessage("annual.one.time.bonus.footer", locale), MoneyUtil.format(taxableAnnualOneTimeBonus)))
            .build();
        return new TraceableTaxBaseAmount(taxableAnnualOneTimeBonus, traceLog);
    }
}
