package com.yahaha.iit.calc;

public class AnnualComprehensiveIncomeOnlyTaxRoutine extends TaxRoutineImpl {
    public AnnualComprehensiveIncomeOnlyTaxRoutine() {
        super(new AnnualComprehensiveIncomeAssessor(), new AnnualComprehensiveIncomeTaxCalculator());
    }

    @Override
    public RoutineCode getRoutineCode() {
        return RoutineCode.COMPREHENSIVE_INCOME_ONLY_TAX;
    }
}
