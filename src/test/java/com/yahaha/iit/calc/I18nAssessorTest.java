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
        zhParam.setLocale(Locale.forLanguageTag("zh-CN"));

        TraceableTaxBaseAmount zhResult = assessor.determineTaxableAmount(zhParam);
        String zhHeader = zhResult.getTraceLog().getHeaderMessage().getMessage();

        TaxCalculationParameter enParam = new TaxCalculationParameter();
        enParam.setAnnualOneTimeBonus(1000);
        enParam.setLocale(Locale.forLanguageTag("en-US"));

        TraceableTaxBaseAmount enResult = assessor.determineTaxableAmount(enParam);
        String enHeader = enResult.getTraceLog().getHeaderMessage().getMessage();

        assertThat(zhHeader).isEqualTo("计算全年一次性奖金应纳税部分");
        assertThat(enHeader).isEqualTo("Calculate Taxable Portion of Annual One-Time Bonus");
        assertThat(enHeader).isNotEqualTo(zhHeader);
    }

    @Test
    void when_change_locale_comprehensive_income_message_should_change() {
        AnnualComprehensiveIncomeAssessor assessor = new AnnualComprehensiveIncomeAssessor();

        TaxCalculationParameter zhParam = new TaxCalculationParameter();
        zhParam.setAnnualWageIncome(1000);
        zhParam.setLocale(Locale.forLanguageTag("zh-CN"));

        TraceableTaxBaseAmount zhResult = assessor.determineTaxableAmount(zhParam);
        String zhHeader = zhResult.getTraceLog().getHeaderMessage().getMessage();

        TaxCalculationParameter enParam = new TaxCalculationParameter();
        enParam.setAnnualWageIncome(1000);
        enParam.setLocale(Locale.forLanguageTag("en-US"));

        TraceableTaxBaseAmount enResult = assessor.determineTaxableAmount(enParam);
        String enHeader = enResult.getTraceLog().getHeaderMessage().getMessage();

        assertThat(zhHeader).isEqualTo("计算全年应纳税综合所得额");
        assertThat(enHeader).isEqualTo("Calculate Annual Taxable Comprehensive Income");
        assertThat(enHeader).isNotEqualTo(zhHeader);
    }
}
