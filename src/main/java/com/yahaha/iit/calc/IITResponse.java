package com.yahaha.iit.calc;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Data;

import java.util.Map;

@Data
public class IITResponse {
    @JsonAnyGetter
    private Map<String, TraceableTaxCalculationResult> results;
}
