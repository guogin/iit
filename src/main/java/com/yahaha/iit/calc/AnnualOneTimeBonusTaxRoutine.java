package com.yahaha.iit.calc;

public class AnnualOneTimeBonusTaxRoutine extends TaxRoutineImpl {
    public AnnualOneTimeBonusTaxRoutine() {
        super(new AnnualOneTimeBonusAssessor(), new AnnualOneTimeBonusTaxCalculator());
    }

    @Override
    public RoutineCode getRoutineCode() {
        return RoutineCode.ONE_TIME_BONUS_TAX;
    }
}
