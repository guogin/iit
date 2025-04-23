package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;
import lombok.*;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class SubTotalProcedure implements Procedure {

    private String description;
    private List<Procedure> subProcedures = new ArrayList<>();

    @Override
    public MonetaryAmount execute() {
        return subProcedures.stream()
                              .map(Procedure::execute)
                              .reduce(MonetaryAmount::add)
                              .orElseThrow(() -> new RuntimeException("Subtotal procedure goes wrong"));
    }

    @Override
    public TraceLog explain() {
        List<TraceLog> subTraceLogs = subProcedures.stream()
                .map(Procedure::explain)
                .collect(Collectors.toList());

        DiagnosticMessage headerMessage = new DiagnosticMessage(this.getDescription());
        DiagnosticMessage footerMessage = new DiagnosticMessage("{0}: {1}",
                                                                this.getDescription(),
                                                                MoneyUtil.format(this.execute()));

        TraceLog traceLog = TraceLog.builder()
                .headerMessage(headerMessage)
                .subTraceLogs(subTraceLogs)
                .footerMessage(footerMessage)
                .build();

        return traceLog;
    }
}