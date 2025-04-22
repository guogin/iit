package com.yahaha.iit.calc;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class TaxBaseProcedure implements Procedure {
    private String description;
    private IITRequest request;
    private Function<IITRequest, TraceableAmount> determinationFunction;

    @Override
    public MonetaryAmount execute() {
        TraceableAmount traceableTaxBase = determinationFunction.apply(request);
        return traceableTaxBase.getAmount();
    }

    @Override
    public List<DiagnosticMessage> explain() {
        List<DiagnosticMessage> messages = new ArrayList<>();
        messages.add(new DiagnosticMessage(description));

        TraceableAmount traceableTaxBase = determinationFunction.apply(request);
        messages.addAll(traceableTaxBase.getTraceLogs());

        return messages;
    }
}
