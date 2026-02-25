package com.yahaha.iit.calc;

import java.util.ArrayList;
import java.util.List;

public final class TaxProcedureFactory2024 implements TaxProcedureFactory {
    @Override
    public TaxProcedure create(TaxCalculationParameter parameter) {
        List<TaxRoutine> routines = new ArrayList<>();

        if (parameter.getBonusTaxationOption() == BonusTaxationOption.ONE_TIME_TAXATION) {
            routines.add(new AnnualIncomeTaxRoutine());
            routines.add(new AnnualBonusTaxRoutine());
        }

        if (parameter.getBonusTaxationOption() == BonusTaxationOption.INTEGRATED_TAXATION) {
            routines.add(new AnnualIntegratedIncomeTaxRoutine());
        }

        return new TaxProcedure(routines);
    }
}
