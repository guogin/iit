package com.yahaha.iit.calc;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import javax.money.MonetaryAmount;


import static org.assertj.core.api.Assertions.assertThat;

public class TaxCalculationParameterTest {
    static MonetaryAmount ZERO = Money.of(0, "CNY");

    @Test
    void when_getAmount_should_never_be_null(){
        TaxCalculationParameter parameter = new TaxCalculationParameter();

        assertThat(parameter.getAnnualWageIncome()).isEqualTo(ZERO);
        assertThat(parameter.getAnnualOneTimeBonus()).isEqualTo(ZERO);
        assertThat(parameter.getServiceRemuneration()).isEqualTo(ZERO);
        assertThat(parameter.getRoyaltyFees()).isEqualTo(ZERO);
        assertThat(parameter.getAuthorsRemuneration()).isEqualTo(ZERO);
        assertThat(parameter.getSpecialDeductions()).isEqualTo(ZERO);
        assertThat(parameter.getAdditionalSpecialDeductions()).isEqualTo(ZERO);
        assertThat(parameter.getOtherDeductions()).isEqualTo(ZERO);
    }

    @Test
    void when_not_specified_then_getBonusTaxationMethod_should_have_default_value() {
        TaxCalculationParameter parameter = new TaxCalculationParameter();

        assertThat(parameter.getBonusTaxationOption()).isNotNull();
    }
}
