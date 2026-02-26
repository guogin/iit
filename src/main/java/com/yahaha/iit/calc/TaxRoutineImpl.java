package com.yahaha.iit.calc;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Locale;

public abstract class TaxRoutineImpl implements TaxRoutine {
    private final TaxableIncomeAssessor taxBaseAssessor;
    private final TaxCalculator taxCalculator;

    public TaxRoutineImpl(TaxableIncomeAssessor taxBaseAssessor, TaxCalculator taxCalculator) {
        this.taxBaseAssessor = taxBaseAssessor;
        this.taxCalculator = taxCalculator;
    }

    @Override
    public TraceableTaxCalculationResultItem execute(TaxCalculationParameter parameter, Locale locale) {
        // Always follow the same routine: Determine base, and then calculate tax
        Locale resolvedLocale = locale == null ? Locale.SIMPLIFIED_CHINESE : locale;
        TraceableTaxBaseAmount taxBase = taxBaseAssessor.determineTaxableAmount(parameter, resolvedLocale);
        TraceableTaxCalculationResultItem taxCalculationResultItem = taxCalculator.calculate(taxBase.getAmount(), resolvedLocale);

        MonetaryAmount taxBaseAmount = taxCalculationResultItem.getTaxBaseAmount();
        MonetaryAmount taxAmount = taxCalculationResultItem.getTaxAmount();
        BigDecimal taxRate = taxCalculationResultItem.getTaxRate();
        TraceLog traceLog = TraceLog.builder()
                .subTraceLogs(Arrays.asList(taxBase.getTraceLog(), taxCalculationResultItem.getTraceLog()))
                .build();

        return new TraceableTaxCalculationResultItem(taxBaseAmount, taxAmount, taxRate, traceLog);
    }
}
