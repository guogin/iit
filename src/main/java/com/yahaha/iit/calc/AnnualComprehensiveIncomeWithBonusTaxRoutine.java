package com.yahaha.iit.calc;

public class AnnualComprehensiveIncomeWithBonusTaxRoutine extends TaxRoutineImpl {
    public AnnualComprehensiveIncomeWithBonusTaxRoutine() {
        super(new AnnualComprehensiveIncomeAssessor(), new AnnualComprehensiveIncomeTaxCalculator());
    }

    @Override
    public RoutineCode getRoutineCode() {
        return RoutineCode.COMPREHENSIVE_INCOME_WITH_BONUS_TAX;
    }
}
