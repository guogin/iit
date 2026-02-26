package com.yahaha.iit.calc;

import java.util.Locale;

public interface TaxableIncomeAssessor {
    TraceableTaxBaseAmount determineTaxableAmount(TaxCalculationParameter parameter, Locale locale);
}
