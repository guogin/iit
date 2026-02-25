package com.yahaha.iit.calc;

public class AnnualIntegratedIncomeTaxRoutine extends TaxRoutineImpl {
    public AnnualIntegratedIncomeTaxRoutine() {
        super(new AnnualComprehensiveIncomeAssessor(), new AnnualComprehensiveIncomeTaxCalculator());
    }

    @Override
    public RoutineCode getRoutineCode() {
        return RoutineCode.INTEGRATED_INCOME_TAX;
    }
}
