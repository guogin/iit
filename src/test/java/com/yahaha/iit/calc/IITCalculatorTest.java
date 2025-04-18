package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IITCalculatorTest {
    IITCalculator calculator;

    @BeforeEach
    void init() {
        this.calculator = new IITCalculatorImpl2024();
    }

    @Test
    void when_income_is_zero_then_tax_should_be_zero() {
        IITRequest request = new IITRequest();
        IITResult result = calculator.calculate(request);

        assertThat(result.getTotalTaxAmount()).isEqualTo(MoneyUtil.ZERO);
    }

    @Test
    void when_income_6000_monthly_and_10000_one_time_bonus_then_tax_should_be_660() {
        IITRequest request = new IITRequest();
        request.setAnnualWageIncome(6000 * 12);
        request.setAnnualOneTimeBonus(10000);
        request.setBonusTaxationMethod(BonusTaxationMethod.ONE_TIME_TAXATION);

        IITResult result = calculator.calculate(request);

        assertThat(result.getTotalTaxAmount()).isEqualTo(MoneyUtil.toAmount(660));
    }

    @Test
    void when_income_6000_monthly_and_10000_integrated_bonus_then_tax_should_be_660() {
        IITRequest request = new IITRequest();
        request.setAnnualWageIncome(6000 * 12);
        request.setAnnualOneTimeBonus(10000);
        request.setBonusTaxationMethod(BonusTaxationMethod.INTEGRATED_TAXATION);

        IITResult result = calculator.calculate(request);

        assertThat(result.getTotalTaxAmount()).isEqualTo(MoneyUtil.toAmount(660));
    }

    @Test
    void when_income_10000_monthly_and_50000_one_time_bonus_then_tax_should_be_8270() {
        IITRequest request = new IITRequest();
        request.setAnnualWageIncome(10000 * 12);
        request.setAnnualOneTimeBonus(50000);
        request.setBonusTaxationMethod(BonusTaxationMethod.ONE_TIME_TAXATION);

        IITResult result = calculator.calculate(request);

        assertThat(result.getTotalTaxAmount()).isEqualTo(MoneyUtil.toAmount(8270));
    }
}
