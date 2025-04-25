package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class IITCalculatorTest {
    IITCalculator calculator;

    @BeforeEach
    void init() {
        this.calculator = new IITCalculatorImpl2024();
    }

    @Test
    void when_income_is_zero_then_tax_should_be_zero() {
        TaxCalculationParameter parameter = new TaxCalculationParameter();
        TraceableTaxCalculationResult result = calculator.calculate(parameter);

        assertThat(result.getTotalTaxAmount()).isEqualTo(MoneyUtil.ZERO);
    }

    @Test
    void when_income_6k_monthly_and_10k_one_time_bonus_then_tax_should_be_660() {
        TaxCalculationParameter parameter = new TaxCalculationParameter();
        parameter.setAnnualWageIncome(6000 * 12);
        parameter.setAnnualOneTimeBonus(10000);
        parameter.setBonusTaxationOption(BonusTaxationOption.ONE_TIME_TAXATION);

        TraceableTaxCalculationResult result = calculator.calculate(parameter);

        assertThat(result.getTotalTaxAmount()).isEqualTo(MoneyUtil.toAmount(660));
    }

    @Test
    void when_income_6k_monthly_and_10k_integrated_bonus_then_tax_should_be_660() {
        TaxCalculationParameter parameter = new TaxCalculationParameter();
        parameter.setAnnualWageIncome(6000 * 12);
        parameter.setAnnualOneTimeBonus(10000);
        parameter.setBonusTaxationOption(BonusTaxationOption.INTEGRATED_TAXATION);

        TraceableTaxCalculationResult result = calculator.calculate(parameter);

        assertThat(result.getTotalTaxAmount()).isEqualTo(MoneyUtil.toAmount(660));
    }

    @Test
    void when_income_10k_monthly_and_50k_one_time_bonus_then_tax_should_be_8270() {
        TaxCalculationParameter parameter = new TaxCalculationParameter();
        parameter.setAnnualWageIncome(10000 * 12);
        parameter.setAnnualOneTimeBonus(50000);
        parameter.setBonusTaxationOption(BonusTaxationOption.ONE_TIME_TAXATION);

        TraceableTaxCalculationResult result = calculator.calculate(parameter);

        assertThat(result.getTotalTaxAmount()).isEqualTo(MoneyUtil.toAmount(8270));
    }

    @Test
    void when_income_10k_monthly_and_50k_integrated_bonus_then_tax_should_be_8480() {
        TaxCalculationParameter parameter = new TaxCalculationParameter();
        parameter.setAnnualWageIncome(10000 * 12);
        parameter.setAnnualOneTimeBonus(50000);
        parameter.setBonusTaxationOption(BonusTaxationOption.INTEGRATED_TAXATION);

        TraceableTaxCalculationResult result = calculator.calculate(parameter);

        assertThat(result.getTotalTaxAmount()).isEqualTo(MoneyUtil.toAmount(8480));
    }

    @Test
    void when_income_18k_monthly_and_300k_one_time_bonus_then_tax_should_be_72870() {
        TaxCalculationParameter parameter = new TaxCalculationParameter();
        parameter.setAnnualWageIncome(18000 * 12);
        parameter.setAnnualOneTimeBonus(300000);
        parameter.setBonusTaxationOption(BonusTaxationOption.ONE_TIME_TAXATION);

        TraceableTaxCalculationResult result = calculator.calculate(parameter);

        assertThat(result.getTotalTaxAmount()).isEqualTo(MoneyUtil.toAmount(72870));
    }

    @Test
    void when_income_18k_monthly_and_300k_integrated_bonus_then_tax_should_be_83880() {
        TaxCalculationParameter parameter = new TaxCalculationParameter();
        parameter.setAnnualWageIncome(18000 * 12);
        parameter.setAnnualOneTimeBonus(300000);
        parameter.setBonusTaxationOption(BonusTaxationOption.INTEGRATED_TAXATION);

        TraceableTaxCalculationResult result = calculator.calculate(parameter);

        assertThat(result.getTotalTaxAmount()).isEqualTo(MoneyUtil.toAmount(83880));
    }

    @Test
    void when_simulate_should_calculate_using_two_options() {
        IITRequest request = new IITRequest();
        request.setAnnualWageIncome(BigDecimal.valueOf(18000 * 12));
        request.setAnnualOneTimeBonus(BigDecimal.valueOf(300000));

        IITResponse response = calculator.simulate(request);
        Map<String, TraceableTaxCalculationResult> resultMap = response.getResults();

        assertThat(resultMap).hasSize(2);
        assertThat(resultMap).containsKey(IITCalculator.ONE_TIME_TAXATION);
        assertThat(resultMap).containsKey(IITCalculator.INTEGRATED_TAXATION);

        TraceableTaxCalculationResult result1 = resultMap.get(IITCalculator.ONE_TIME_TAXATION);
        TraceableTaxCalculationResult result2 = resultMap.get(IITCalculator.INTEGRATED_TAXATION);

        assertThat(result1.getTotalTaxAmount()).isEqualTo(MoneyUtil.toAmount(72870));
        assertThat(result2.getTotalTaxAmount()).isEqualTo(MoneyUtil.toAmount(83880));
    }
}
