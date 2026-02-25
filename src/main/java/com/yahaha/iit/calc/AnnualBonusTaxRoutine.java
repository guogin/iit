package com.yahaha.iit.calc;

public class AnnualBonusTaxRoutine extends TaxRoutineImpl {
    public AnnualBonusTaxRoutine() {
        super(new AnnualOneTimeBonusAssessor(), new AnnualOneTimeBonusTaxCalculator());
    }

    @Override
    public RoutineCode getRoutineCode() {
        return RoutineCode.BONUS_TAX;
    }
}
