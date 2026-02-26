package com.yahaha.iit.calc;

import javax.money.MonetaryAmount;
import java.util.Locale;

public interface TaxCalculator {
    TraceableTaxCalculationResultItem calculate(MonetaryAmount taxBaseAmount, Locale locale);
}
