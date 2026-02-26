package com.yahaha.iit.calc;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class I18nAssessorTest {
    @Test
    void when_change_locale_one_time_bonus_message_should_change() {
        AnnualOneTimeBonusAssessor assessor = new AnnualOneTimeBonusAssessor();

        TaxCalculationParameter zhParam = new TaxCalculationParameter();
        zhParam.setAnnualOneTimeBonus(1000);

        TraceableTaxBaseAmount zhResult = assessor.determineTaxableAmount(zhParam, Locale.forLanguageTag("zh-CN"));
        String zhHeader = zhResult.getTraceLog().getHeaderMessage().getMessage();

        TaxCalculationParameter enParam = new TaxCalculationParameter();
        enParam.setAnnualOneTimeBonus(1000);

        TraceableTaxBaseAmount enResult = assessor.determineTaxableAmount(enParam, Locale.forLanguageTag("en-US"));
        String enHeader = enResult.getTraceLog().getHeaderMessage().getMessage();

        assertThat(zhHeader).isEqualTo("计算全年一次性奖金应纳税部分");
        assertThat(enHeader).isEqualTo("Calculate Taxable Portion of Annual One-Time Bonus");
        assertThat(enHeader).isNotEqualTo(zhHeader);
    }

    @Test
    void when_change_locale_comprehensive_income_message_should_change() {
        AnnualComprehensiveIncomeAssessor assessor = new AnnualComprehensiveIncomeAssessor();

        TaxCalculationParameter param = new TaxCalculationParameter();
        param.setAnnualWageIncome(1000);

        TraceableTaxBaseAmount zhResult = assessor.determineTaxableAmount(param, Locale.forLanguageTag("zh-CN"));
        String zhHeader = zhResult.getTraceLog().getHeaderMessage().getMessage();

        TraceableTaxBaseAmount enResult = assessor.determineTaxableAmount(param, Locale.forLanguageTag("en-US"));
        String enHeader = enResult.getTraceLog().getHeaderMessage().getMessage();

        assertThat(zhHeader).isEqualTo("计算全年应纳税综合所得额");
        assertThat(enHeader).isEqualTo("Calculate Annual Taxable Comprehensive Income");
        assertThat(enHeader).isNotEqualTo(zhHeader);
    }
}
