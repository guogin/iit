package com.yahaha.iit.calc;

public interface TaxRoutine {
    TraceableTaxCalculationResultItem execute(TaxCalculationParameter request);
}
