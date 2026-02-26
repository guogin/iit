package com.yahaha.iit.calc;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.util.Locale;

public class DummyTaxCalculator implements TaxCalculator {
    public static final String CALCULATOR_TRACE_LOG_MESSAGE = "Tax calculated";

    @Override
    public TraceableTaxCalculationResultItem calculate(MonetaryAmount taxBaseAmount, Locale locale) {
        BigDecimal taxRate = BigDecimal.valueOf(0.1);
        MonetaryAmount taxAmount = taxBaseAmount.multiply(taxRate);
        TraceLog traceLog = TraceLog.builder()
                .headerMessage(new DiagnosticMessage(CALCULATOR_TRACE_LOG_MESSAGE))
                .build();
        return new TraceableTaxCalculationResultItem(taxBaseAmount, taxAmount, taxRate, traceLog);
    }
}
