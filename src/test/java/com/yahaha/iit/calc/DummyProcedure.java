package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DummyProcedure implements Procedure {
    private MonetaryAmount amount;

    @Override
    public MonetaryAmount execute() {
        return amount;
    }

    @Override
    public List<DiagnosticMessage> explain() {
        DiagnosticMessage message = new DiagnosticMessage("Dummy Procedure, amount = {0}", MoneyUtil.format(this.amount));
        List<DiagnosticMessage> messages = new ArrayList<>();
        messages.add(message);
        return messages;
    }
}
