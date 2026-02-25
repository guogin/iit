package com.yahaha.iit.calc;

public interface TaxRoutine {
    RoutineCode getRoutineCode();

    TraceableTaxCalculationResultItem execute(TaxCalculationParameter request);
}
