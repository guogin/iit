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
}
