package com.yahaha.iit.calc;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import javax.money.MonetaryAmount;

import static org.assertj.core.api.Assertions.assertThat;

public class TaxCalculationParameterTest {
    static MonetaryAmount ZERO = Money.of(0, "CNY");

    @Test
    void when_getAmount_should_never_be_null(){
        TaxCalculationParameter request = new TaxCalculationParameter();

        assertThat(request.getAnnualWageIncome()).isEqualTo(ZERO);
        assertThat(request.getAnnualOneTimeBonus()).isEqualTo(ZERO);
        assertThat(request.getServiceRemuneration()).isEqualTo(ZERO);
        assertThat(request.getRoyaltyFees()).isEqualTo(ZERO);
        assertThat(request.getAuthorsRemuneration()).isEqualTo(ZERO);
        assertThat(request.getSpecialDeductions()).isEqualTo(ZERO);
        assertThat(request.getAdditionalSpecialDeductions()).isEqualTo(ZERO);
        assertThat(request.getOtherDeductions()).isEqualTo(ZERO);
    }

    @Test
    void when_not_specified_then_getBonusTaxationMethod_should_have_default_value() {
        TaxCalculationParameter request = new TaxCalculationParameter();

        assertThat(request.getBonusTaxationOption()).isNotNull();
    }
}
