package com.yahaha.iit.calc;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import javax.money.MonetaryAmount;

import static org.assertj.core.api.Assertions.assertThat;

public class IITResultTest {
    @Test
    void when_getTotalTaxAmount_should_return_total() {
        IITResult result = IITResult.builder()
                .taxAmountForAnnualBonus(1000L)
                .taxAmountForAnnualIncome(2000L)
                .build();

        assertThat(result.getTotalTaxAmount()).isEqualTo(Money.of(3000L, "CNY"));
    }

    @Test
    void when_some_is_null_getTotalTaxAmount_should_return_total() {
        IITResult result = IITResult.builder()
                .taxAmountForAnnualBonus(1000L)
                .build();

        assertThat(result.getTotalTaxAmount()).isEqualTo(Money.of(1000L, "CNY"));
    }

    @Test
    void when_build_using_money_should_also_work() {
        MonetaryAmount OneThousandCNY = Money.of(1000, "CNY");
        MonetaryAmount OneHundredCNY = Money.of(100, "CNY");
        IITResult result = IITResult.builder()
                .taxBaseForAnnualIncome(OneThousandCNY)
                .taxAmountForAnnualIncome(OneHundredCNY)
                .build();

        assertThat(result.getTaxBaseForAnnualIncome()).isEqualTo(OneThousandCNY);
        assertThat(result.getTotalTaxAmount()).isEqualTo(OneHundredCNY);
    }
}
