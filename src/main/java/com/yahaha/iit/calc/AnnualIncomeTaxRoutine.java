package com.yahaha.iit.calc;

public class AnnualIncomeTaxRoutine extends TaxRoutineImpl {
    public AnnualIncomeTaxRoutine() {
        super(new AnnualComprehensiveIncomeAssessor(), new AnnualComprehensiveIncomeTaxCalculator());
    }

    @Override
    public RoutineCode getRoutineCode() {
        return RoutineCode.INCOME_TAX;
    }
}
