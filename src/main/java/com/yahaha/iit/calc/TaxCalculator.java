package com.yahaha.iit.calc;

import javax.money.MonetaryAmount;

public interface TaxCalculator {
    TraceableTaxCalculationResultItem calculate(MonetaryAmount taxBaseAmount);
}
