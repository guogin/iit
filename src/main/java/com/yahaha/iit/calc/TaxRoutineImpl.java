package com.yahaha.iit.calc;

public class TaxRoutineImpl implements TaxRoutine {
    private TaxableIncomeAssessor taxBaseAssessor;
    private ProgressiveTax taxCalculator;

    public TaxRoutineImpl(TaxableIncomeAssessor taxBaseAssessor, ProgressiveTax taxCalculator) {
        this.taxBaseAssessor = taxBaseAssessor;
        this.taxCalculator = taxCalculator;
    }

    @Override
    public TraceableTaxCalculationResultItem execute(TaxCalculationParameter request) {
        // Always follow the same routine: Determine base, and then calculate tax
        TraceableTaxBaseAmount taxBase = taxBaseAssessor.determineTaxableAmount(request);
        TraceableTaxCalculationResultItem taxCalculationResultItem = taxCalculator.calculate(taxBase.getAmount());

        return taxCalculationResultItem;
    }
}
