package com.yahaha.iit.calc;

import lombok.Data;

import java.util.Map;

@Data
public class IITResponse {
    private Map<String, TraceableTaxCalculationResult> results;
}
