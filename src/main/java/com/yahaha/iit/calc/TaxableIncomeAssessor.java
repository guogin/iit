package com.yahaha.iit.calc;

public interface TaxableIncomeAssessor {
    TraceableTaxBaseAmount determineTaxableAmount(TaxCalculationParameter request);
}
