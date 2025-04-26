package com.yahaha.iit.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class TaxRoutineTest {
    private TaxableIncomeAssessor assessor;
    private TaxCalculator calculator;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        assessor = new DummyTaxableIncomeAssessor();
        calculator = new DummyTaxCalculator();
        objectMapper = new ObjectMapper();
    }

    @Test
    void when_execute_should_combine_tracelogs() throws Exception {
        TaxRoutine routine = new TaxRoutineImpl(assessor, calculator);
        TaxCalculationParameter parameter = new TaxCalculationParameter();

        TraceableTaxCalculationResultItem resultItem = routine.execute(parameter);
        TraceLog traceLog = resultItem.getTraceLog();

        assertThat(traceLog).isNotNull();
        assertThat(traceLog.getSubTraceLogs()).hasSize(2);

        String json = objectMapper.writeValueAsString(traceLog);
        assertThat(json).contains(DummyTaxableIncomeAssessor.ASSESSOR_TRACE_LOG_MESSAGE)
                .contains(DummyTaxCalculator.CALCULATOR_TRACE_LOG_MESSAGE);
    }
}
