package com.yahaha.iit.calc;

import java.util.ArrayList;
import java.util.List;

public final class TaxProcedureFactory2024 implements TaxProcedureFactory {
    @Override
    public TaxProcedure create(TaxCalculationParameter parameter) {
        List<TaxRoutine> routines = new ArrayList<>();

        TaxRoutine annualComprehensiveIncomeTaxRoutine = new TaxRoutineImpl(
                new AnnualComprehensiveIncomeAssessor(),
                new AnnualComprehensiveIncomeTaxCalculator()
        );
        routines.add(annualComprehensiveIncomeTaxRoutine);

        if (parameter.getBonusTaxationOption() == BonusTaxationOption.ONE_TIME_TAXATION) {
            TaxRoutine annualOneTimeBonusTaxRoutine = new TaxRoutineImpl(
                    new AnnualOneTimeBonusAssessor(),
                    new AnnualOneTimeBonusTaxCalculator()
            );
            routines.add(annualOneTimeBonusTaxRoutine);
        }

        return new TaxProcedure(routines);
    }
}
