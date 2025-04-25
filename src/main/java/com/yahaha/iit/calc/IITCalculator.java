package com.yahaha.iit.calc;

public interface IITCalculator {
    String ONE_TIME_TAXATION = "单独计税";
    String INTEGRATED_TAXATION = "并入全年综合收入计税";
    IITResponse simulate(IITRequest request);
    TraceableTaxCalculationResult calculate(TaxCalculationParameter parameter);
}
