package com.yahaha.iit.calc;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SubTotalProcedureTest {
    @Test
    void when_subtotal_is_executed_should_return_subtotal() {
        List<Procedure> dummyProcedures = new ArrayList<>();
        dummyProcedures.add(new DummyProcedure(Money.of(1, "CNY")));
        dummyProcedures.add(new DummyProcedure(Money.of(100, "CNY")));
        dummyProcedures.add(new DummyProcedure(Money.of(23.5, "CNY")));

        SubTotalProcedure subtotalProcedure = new SubTotalProcedure("Test", dummyProcedures);

        MonetaryAmount amount = subtotalProcedure.execute();

        assertThat(amount).isEqualTo(Money.of(124.5, "CNY"));
    }

    @Test
    void when_subtotal_is_explained_should_be_structured() {
        List<Procedure> dummyProcedures = new ArrayList<>();
        dummyProcedures.add(new DummyProcedure(Money.of(1, "CNY")));
        dummyProcedures.add(new DummyProcedure(Money.of(100, "CNY")));
        dummyProcedures.add(new DummyProcedure(Money.of(23.5, "CNY")));

        SubTotalProcedure subtotalProcedure = new SubTotalProcedure("Test", dummyProcedures);

        List<DiagnosticMessage> messages = subtotalProcedure.explain();
        assertThat(messages).hasSize(4);
        assertThat(messages.get(0).getMessage()).contains("¥").contains("1.00");
        assertThat(messages.get(1).getMessage()).contains("¥").contains("100.00");
        assertThat(messages.get(3).getMessage()).contains("¥").contains("124.50");
    }
}
