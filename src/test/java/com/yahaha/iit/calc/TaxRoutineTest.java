package com.yahaha.iit.calc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

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
        TaxRoutine routine = new DummyRoutine(assessor, calculator);
        TaxCalculationParameter parameter = new TaxCalculationParameter();

        TraceableTaxCalculationResultItem resultItem = routine.execute(parameter, Locale.SIMPLIFIED_CHINESE);
        TraceLog traceLog = resultItem.getTraceLog();

        assertThat(traceLog).isNotNull();
        assertThat(traceLog.getSubTraceLogs()).hasSize(2);

        String json = objectMapper.writeValueAsString(traceLog);
        assertThat(json).contains(DummyTaxableIncomeAssessor.ASSESSOR_TRACE_LOG_MESSAGE)
                .contains(DummyTaxCalculator.CALCULATOR_TRACE_LOG_MESSAGE);
    }

    private static class DummyRoutine extends TaxRoutineImpl {
        public DummyRoutine(TaxableIncomeAssessor assessor, TaxCalculator calculator) {
            super(assessor, calculator);
        }

        @Override
        public RoutineCode getRoutineCode() {
            return RoutineCode.COMPREHENSIVE_INCOME_ONLY_TAX;
        }
    }
}
