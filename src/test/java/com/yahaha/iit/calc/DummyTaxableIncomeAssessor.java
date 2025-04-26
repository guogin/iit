package com.yahaha.iit.calc;

import javax.money.MonetaryAmount;

public class DummyTaxableIncomeAssessor implements TaxableIncomeAssessor {
    public static final String ASSESSOR_TRACE_LOG_MESSAGE = "Tax base assessed";

    @Override
    public TraceableTaxBaseAmount determineTaxableAmount(TaxCalculationParameter request) {
        MonetaryAmount taxBaseAmount = request.getAnnualWageIncome();
        TraceLog traceLog = TraceLog.builder()
                .headerMessage(new DiagnosticMessage(ASSESSOR_TRACE_LOG_MESSAGE))
                .build();
        return new TraceableTaxBaseAmount(taxBaseAmount, traceLog);
    }
}
