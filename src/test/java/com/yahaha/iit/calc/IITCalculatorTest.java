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
        TaxCalculationParameter request = new TaxCalculationParameter();
        TraceableTaxCalculationResult result = calculator.calculate(request);

        assertThat(result.getTotalTaxAmount()).isEqualTo(MoneyUtil.ZERO);
    }

    @Test
    void when_income_6k_monthly_and_10k_one_time_bonus_then_tax_should_be_660() {
        TaxCalculationParameter request = new TaxCalculationParameter();
        request.setAnnualWageIncome(6000 * 12);
        request.setAnnualOneTimeBonus(10000);
        request.setBonusTaxationOption(BonusTaxationOption.ONE_TIME_TAXATION);

        TraceableTaxCalculationResult result = calculator.calculate(request);

        assertThat(result.getTotalTaxAmount()).isEqualTo(MoneyUtil.toAmount(660));
    }

    @Test
    void when_income_6k_monthly_and_10k_integrated_bonus_then_tax_should_be_660() {
        TaxCalculationParameter request = new TaxCalculationParameter();
        request.setAnnualWageIncome(6000 * 12);
        request.setAnnualOneTimeBonus(10000);
        request.setBonusTaxationOption(BonusTaxationOption.INTEGRATED_TAXATION);

        TraceableTaxCalculationResult result = calculator.calculate(request);

        assertThat(result.getTotalTaxAmount()).isEqualTo(MoneyUtil.toAmount(660));
    }

    @Test
    void when_income_10k_monthly_and_50k_one_time_bonus_then_tax_should_be_8270() {
        TaxCalculationParameter request = new TaxCalculationParameter();
        request.setAnnualWageIncome(10000 * 12);
        request.setAnnualOneTimeBonus(50000);
        request.setBonusTaxationOption(BonusTaxationOption.ONE_TIME_TAXATION);

        TraceableTaxCalculationResult result = calculator.calculate(request);

        assertThat(result.getTotalTaxAmount()).isEqualTo(MoneyUtil.toAmount(8270));
    }

    @Test
    void when_income_10k_monthly_and_50k_integrated_bonus_then_tax_should_be_8480() {
        TaxCalculationParameter request = new TaxCalculationParameter();
        request.setAnnualWageIncome(10000 * 12);
        request.setAnnualOneTimeBonus(50000);
        request.setBonusTaxationOption(BonusTaxationOption.INTEGRATED_TAXATION);

        TraceableTaxCalculationResult result = calculator.calculate(request);

        assertThat(result.getTotalTaxAmount()).isEqualTo(MoneyUtil.toAmount(8480));
    }

    @Test
    void when_income_18k_monthly_and_300k_one_time_bonus_then_tax_should_be_72870() {
        TaxCalculationParameter request = new TaxCalculationParameter();
        request.setAnnualWageIncome(18000 * 12);
        request.setAnnualOneTimeBonus(300000);
        request.setBonusTaxationOption(BonusTaxationOption.ONE_TIME_TAXATION);

        TraceableTaxCalculationResult result = calculator.calculate(request);

        assertThat(result.getTotalTaxAmount()).isEqualTo(MoneyUtil.toAmount(72870));
    }

    @Test
    void when_income_18k_monthly_and_300k_integrated_bonus_then_tax_should_be_83880() {
        TaxCalculationParameter request = new TaxCalculationParameter();
        request.setAnnualWageIncome(18000 * 12);
        request.setAnnualOneTimeBonus(300000);
        request.setBonusTaxationOption(BonusTaxationOption.INTEGRATED_TAXATION);

        TraceableTaxCalculationResult result = calculator.calculate(request);

        assertThat(result.getTotalTaxAmount()).isEqualTo(MoneyUtil.toAmount(83880));
    }
}
