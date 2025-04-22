package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;
import lombok.*;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.List;

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
    public List<DiagnosticMessage> explain() {
        List<DiagnosticMessage> messages = subProcedures.stream()
                .map(Procedure::explain)
                .reduce(new ArrayList<>(), (a, b) -> { a.addAll(b); return a; });

        messages.add(new DiagnosticMessage("{0}: {1}",
                this.getDescription(),
                MoneyUtil.format(this.execute())));

        return messages;
    }
}