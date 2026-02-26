package com.yahaha.iit.calc;

import java.util.Locale;

public interface TaxRoutine {
    RoutineCode getRoutineCode();

    TraceableTaxCalculationResultItem execute(TaxCalculationParameter parameter, Locale locale);
}
