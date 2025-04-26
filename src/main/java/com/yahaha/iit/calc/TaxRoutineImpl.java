package com.yahaha.iit.calc;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.util.Arrays;

public class TaxRoutineImpl implements TaxRoutine {
    private TaxableIncomeAssessor taxBaseAssessor;
    private TaxCalculator taxCalculator;

    public TaxRoutineImpl(TaxableIncomeAssessor taxBaseAssessor, TaxCalculator taxCalculator) {
        this.taxBaseAssessor = taxBaseAssessor;
        this.taxCalculator = taxCalculator;
    }

    @Override
    public TraceableTaxCalculationResultItem execute(TaxCalculationParameter request) {
        // Always follow the same routine: Determine base, and then calculate tax
        TraceableTaxBaseAmount taxBase = taxBaseAssessor.determineTaxableAmount(request);
        TraceableTaxCalculationResultItem taxCalculationResultItem = taxCalculator.calculate(taxBase.getAmount());

        MonetaryAmount taxBaseAmount = taxCalculationResultItem.getTaxBaseAmount();
        MonetaryAmount taxAmount = taxCalculationResultItem.getTaxAmount();
        BigDecimal taxRate = taxCalculationResultItem.getTaxRate();
        TraceLog traceLog = TraceLog.builder()
                .subTraceLogs(Arrays.asList(taxBase.getTraceLog(), taxCalculationResultItem.getTraceLog()))
                .build();

        return new TraceableTaxCalculationResultItem(taxBaseAmount, taxAmount, taxRate, traceLog);
    }
}
