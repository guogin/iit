package com.yahaha.iit.calc;

public interface IITCalculator {
    IITResponse simulate(IITRequest request);
    TraceableTaxCalculationResult calculate(TaxCalculationParameter parameter);
}
