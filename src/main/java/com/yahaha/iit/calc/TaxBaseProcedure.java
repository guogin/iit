package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class TaxBaseProcedure implements Procedure {
    private String description;
    private IITRequest request;
    private TaxableIncomeAssessor assessor;

    @Override
    public MonetaryAmount execute() {
        TraceableAmount traceableTaxBase = assessor.determineTaxableAmount(request);
        return traceableTaxBase.getAmount();
    }

    @Override
    public TraceLog explain() {
        List<DiagnosticMessage> messages = new ArrayList<>();
        messages.add(new DiagnosticMessage(description));

        TraceableAmount traceableTaxBase = assessor.determineTaxableAmount(request);
        TraceLog subTraceLog = traceableTaxBase.getTraceLog();
        List<TraceLog> subTraceLogs = new ArrayList<>();
        subTraceLogs.add(subTraceLog);

        TraceLog traceLog = TraceLog.builder()
                .headerMessage(new DiagnosticMessage(this.getDescription()))
                .subTraceLogs(subTraceLogs)
                .footerMessage(new DiagnosticMessage("{0}: {1}", this.getDescription(), MoneyUtil.format(traceableTaxBase.getAmount())))
                .build();

        return traceLog;
    }
}
