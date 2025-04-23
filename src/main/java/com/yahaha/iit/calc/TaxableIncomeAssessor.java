package com.yahaha.iit.calc;

public interface TaxableIncomeAssessor {
    TraceableAmount determineTaxableAmount(IITRequest request);
}
