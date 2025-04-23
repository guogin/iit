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

        TraceLog traceLog = subtotalProcedure.explain();
        List<TraceLog> subTraceLogs = traceLog.getSubTraceLogs();

        assertThat(subTraceLogs).hasSize(3);
        assertThat(subTraceLogs.get(0).getFooterMessage().getMessage()).contains("¥").contains("1.00");
        assertThat(subTraceLogs.get(1).getFooterMessage().getMessage()).contains("¥").contains("100.00");
        assertThat(subTraceLogs.get(2).getFooterMessage().getMessage()).contains("¥").contains("23.50");
        assertThat(traceLog.getFooterMessage().getMessage()).contains("¥").contains("124.50");
    }
}
