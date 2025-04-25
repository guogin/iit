package com.yahaha.iit.calc;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import javax.money.MonetaryAmount;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class TraceableTaxCalculationResultTest {
    private static final MonetaryAmount ONE_THOUSAND_CNY = Money.of(1000, "CNY");
    private static final MonetaryAmount TWO_THOUSAND_CNY = Money.of(2000, "CNY");
    private static final MonetaryAmount THREE_THOUSAND_CNY = Money.of(3000, "CNY");
    private static final MonetaryAmount ONE_HUNDRED_CNY = Money.of(100, "CNY");
    private static final BigDecimal TEN_PERCENT = BigDecimal.valueOf(0.1);

    @Test
    void when_getTotalTaxAmount_should_return_total() {
        TraceableTaxCalculationResultItem item1 = TraceableTaxCalculationResultItem.builder().taxAmount(ONE_THOUSAND_CNY).build();
        TraceableTaxCalculationResultItem item2 = TraceableTaxCalculationResultItem.builder().taxAmount(TWO_THOUSAND_CNY).build();

        TraceableTaxCalculationResult result = TraceableTaxCalculationResult.of(item1, item2);

        assertThat(result.getTotalTaxAmount()).isEqualTo(THREE_THOUSAND_CNY);
    }

    @Test
    void when_some_is_null_getTotalTaxAmount_should_return_total() {
        TraceableTaxCalculationResultItem item1 = TraceableTaxCalculationResultItem.builder().taxAmount(ONE_THOUSAND_CNY).build();
        TraceableTaxCalculationResultItem item2 = TraceableTaxCalculationResultItem.builder().build();

        TraceableTaxCalculationResult result = TraceableTaxCalculationResult.of(item1, item2);

        assertThat(result.getTotalTaxAmount()).isEqualTo(ONE_THOUSAND_CNY);
    }

    @Test
    void when_convert_to_json_should_contain_traceLog() throws Exception {
        TraceableTaxCalculationResultItem item1 = TraceableTaxCalculationResultItem.builder()
                .taxBaseAmount(ONE_THOUSAND_CNY)
                .taxAmount(ONE_HUNDRED_CNY)
                .taxRate(TEN_PERCENT)
                .traceLog(DummyTraceLogProvider.createTraceLog11())
                .build();
        TraceableTaxCalculationResultItem item2 = TraceableTaxCalculationResultItem.builder()
                .taxAmount(ONE_HUNDRED_CNY)
                .traceLog(DummyTraceLogProvider.createTraceLog13())
                .build();

        TraceableTaxCalculationResult result = TraceableTaxCalculationResult.of(item1, item2);

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("MonetaryAmountSerializer",
            new Version(1, 0, 0, null, null, null));
        module.addSerializer(MonetaryAmount.class, new MonetaryAmountSerializer());
        objectMapper.registerModule(module);
        String json = objectMapper.writeValueAsString(result);

        assertThat(json).contains("¥1,000.00");
        assertThat(json).contains("1-3-1");
    }
}
