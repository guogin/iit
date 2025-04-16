package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

@EqualsAndHashCode
@ToString
public class IITResult {
    public static final MonetaryAmount ZERO = MoneyUtil.ZERO;
    
    private MonetaryAmount taxBaseForAnnualIncome;
    private MonetaryAmount taxAmountForAnnualIncome;
    private MonetaryAmount taxBaseForAnnualBonus;
    private MonetaryAmount taxAmountForAnnualBonus;

    public MonetaryAmount getTotalTaxAmount() {
        return getTaxAmountForAnnualIncome().add(getTaxAmountForAnnualBonus());
    }

    public MonetaryAmount getTaxBaseForAnnualIncome() {
        return taxBaseForAnnualIncome == null ? ZERO : taxBaseForAnnualIncome;
    }

    public MonetaryAmount getTaxAmountForAnnualIncome() {
        return taxAmountForAnnualIncome == null ? ZERO : taxAmountForAnnualIncome;
    }

    public MonetaryAmount getTaxBaseForAnnualBonus() {
        return taxBaseForAnnualBonus == null ? ZERO : taxBaseForAnnualBonus;
    }

    public MonetaryAmount getTaxAmountForAnnualBonus() {
        return taxAmountForAnnualBonus == null ? ZERO : taxAmountForAnnualBonus;
    }

    public IITResult(Number taxBaseForAnnualIncome,
                     Number taxAmountForAnnualIncome,
                     Number taxBaseForAnnualBonus,
                     Number taxAmountForAnnualBonus) {
        this.taxBaseForAnnualIncome = MoneyUtil.toAmount(taxBaseForAnnualIncome);
        this.taxAmountForAnnualIncome = MoneyUtil.toAmount(taxAmountForAnnualIncome);
        this.taxBaseForAnnualBonus = MoneyUtil.toAmount(taxBaseForAnnualBonus);
        this.taxAmountForAnnualBonus = MoneyUtil.toAmount(taxAmountForAnnualBonus);
    }

    public static Builder builder() {
        return new Builder();
    }

    @NoArgsConstructor
    public static class Builder {
        private Number taxBaseForAnnualIncome;
        private Number taxAmountForAnnualIncome;
        private Number taxBaseForAnnualBonus;
        private Number taxAmountForAnnualBonus;

        public Builder taxBaseForAnnualIncome(Number taxBaseForAnnualIncome) {
            this.taxBaseForAnnualIncome = taxBaseForAnnualIncome;
            return this;
        }

        public Builder taxAmountForAnnualIncome(Number taxAmountForAnnualIncome) {
            this.taxAmountForAnnualIncome = taxAmountForAnnualIncome;
            return this;
        }

        public Builder taxBaseForAnnualBonus(Number taxBaseForAnnualBonus) {
            this.taxBaseForAnnualBonus = taxBaseForAnnualBonus;
            return this;
        }

        public Builder taxAmountForAnnualBonus(Number taxAmountForAnnualBonus) {
            this.taxAmountForAnnualBonus = taxAmountForAnnualBonus;
            return this;
        }

        public IITResult build() {
            return new IITResult(taxBaseForAnnualIncome,
                    taxAmountForAnnualIncome,
                    taxBaseForAnnualBonus,
                    taxAmountForAnnualBonus);
        }
    }
}
