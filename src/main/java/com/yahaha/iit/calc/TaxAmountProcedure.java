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
public class TaxAmountProcedure implements Procedure {
    private String description;
    private TaxBaseProcedure taxBaseProcedure;
    private ProgressiveTax progressiveTax;

    @Override
    public MonetaryAmount execute() {
        TraceableAmount traceableAmount = progressiveTax.calculate(taxBaseProcedure.execute());
        return traceableAmount.getAmount();
    }

    @Override
    public TraceLog explain() {
        TraceLog baseTraceLog = taxBaseProcedure.explain();

        TraceableAmount traceableAmount = progressiveTax.calculate(taxBaseProcedure.execute());
        TraceLog taxTraceLog = traceableAmount.getTraceLog();

        List<TraceLog> subTraceLogs = new ArrayList<>();
        subTraceLogs.add(baseTraceLog);
        subTraceLogs.add(taxTraceLog);

        TraceLog traceLog = TraceLog.builder()
                .headerMessage(new DiagnosticMessage(this.getDescription()))
                .subTraceLogs(subTraceLogs)
                .footerMessage(new DiagnosticMessage("{0}: {1}", this.getDescription(), MoneyUtil.format(traceableAmount.getAmount())))
                .build();

        return traceLog;
    }
}
