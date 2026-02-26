package com.yahaha.iit.calc;

import java.util.Locale;

public interface IITCalculator {
    IITResponse simulate(IITRequest request);
    default TraceableTaxCalculationResult calculate(TaxCalculationParameter parameter) {
        return calculate(parameter, Locale.SIMPLIFIED_CHINESE);
    }
    TraceableTaxCalculationResult calculate(TaxCalculationParameter parameter, Locale locale);
}
