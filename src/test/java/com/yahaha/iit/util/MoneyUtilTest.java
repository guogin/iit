package com.yahaha.iit.util;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;

public class MoneyUtilTest {

    @Test
    void when_convert_null_should_return_zero() {
        BigDecimal fromValue = null;
        MonetaryAmount toValue = MoneyUtil.toAmount(fromValue);

        assertThat(toValue).isEqualTo(Money.of(0, "CNY"));
    }

    @Test
    void when_convert_long_should_return_amount() {
        Long fromValue = 1234L;
        MonetaryAmount toValue = MoneyUtil.toAmount(fromValue);

        assertThat(toValue).isEqualTo(Money.of(new BigDecimal("1234.00"), "CNY"));
    }
}
