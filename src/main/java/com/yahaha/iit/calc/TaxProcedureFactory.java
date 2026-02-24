package com.yahaha.iit.calc;

public interface TaxProcedureFactory {
    TaxProcedure create(TaxCalculationParameter parameter);
}
