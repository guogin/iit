package com.yahaha.iit.calc;

import javax.money.MonetaryAmount;
import java.util.Locale;

public class DummyTaxableIncomeAssessor implements TaxableIncomeAssessor {
    public static final String ASSESSOR_TRACE_LOG_MESSAGE = "Tax base assessed";

    @Override
    public TraceableTaxBaseAmount determineTaxableAmount(TaxCalculationParameter parameter, Locale locale) {
        MonetaryAmount taxBaseAmount = parameter.getAnnualWageIncome();
        TraceLog traceLog = TraceLog.builder()
                .headerMessage(new DiagnosticMessage(ASSESSOR_TRACE_LOG_MESSAGE))
                .build();
        return new TraceableTaxBaseAmount(taxBaseAmount, traceLog);
    }
}
