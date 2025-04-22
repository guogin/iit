package com.yahaha.iit.calc;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.List;

public class TaxAmountProcedure implements Procedure {
    private TaxBaseProcedure taxBaseProcedure;
    private ProgressiveTax progressiveTax;

    @Override
    public MonetaryAmount execute() {
        TraceableAmount traceableAmount = progressiveTax.calculate(taxBaseProcedure.execute());
        return traceableAmount.getAmount();
    }

    @Override
    public List<DiagnosticMessage> explain() {
        TraceableAmount traceableAmount = progressiveTax.calculate(taxBaseProcedure.execute());

        List<DiagnosticMessage> messages = new ArrayList<>();
        messages.addAll(taxBaseProcedure.explain());

        messages.addAll(traceableAmount.getTraceLogs());

        return messages;
    }
}
