package com.yahaha.iit.calc;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import javax.money.MonetaryAmount;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class IITResultTest {
    private static final MonetaryAmount ONE_THOUSAND_CNY = Money.of(1000, "CNY");
    private static final MonetaryAmount TWO_THOUSAND_CNY = Money.of(2000, "CNY");
    private static final MonetaryAmount THREE_THOUSAND_CNY = Money.of(3000, "CNY");
    private static final MonetaryAmount ONE_HUNDRED_CNY = Money.of(100, "CNY");
    private static final BigDecimal TEN_PERCENT = BigDecimal.valueOf(0.1);

    @Test
    void when_getTotalTaxAmount_should_return_total() {
        TaxItem item1 = TaxItem.builder().taxAmount(ONE_THOUSAND_CNY).build();
        TaxItem item2 = TaxItem.builder().taxAmount(TWO_THOUSAND_CNY).build();

        IITResult result = new IITResult();
        result.setItems(Arrays.asList(item1, item2));

        assertThat(result.getTotalTaxAmount()).isEqualTo(THREE_THOUSAND_CNY);
    }

    @Test
    void when_some_is_null_getTotalTaxAmount_should_return_total() {
        TaxItem item1 = TaxItem.builder().taxAmount(ONE_THOUSAND_CNY).build();
        TaxItem item2 = TaxItem.builder().build();

        IITResult result = new IITResult();
        result.setItems(Arrays.asList(item1, item2));

        assertThat(result.getTotalTaxAmount()).isEqualTo(ONE_THOUSAND_CNY);
    }
}
