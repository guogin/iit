package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    public IITResult(MonetaryAmount taxBaseForAnnualIncome,
                     MonetaryAmount taxAmountForAnnualIncome,
                     MonetaryAmount taxBaseForAnnualBonus,
                     MonetaryAmount taxAmountForAnnualBonus) {
        this.taxBaseForAnnualIncome = taxBaseForAnnualIncome;
        this.taxAmountForAnnualIncome = taxAmountForAnnualIncome;
        this.taxBaseForAnnualBonus = taxBaseForAnnualBonus;
        this.taxAmountForAnnualBonus = taxAmountForAnnualBonus;
    }

    public static Builder builder() {
        return new Builder();
    }

    @NoArgsConstructor
    public static class Builder {
        private MonetaryAmount taxBaseForAnnualIncome;
        private MonetaryAmount taxAmountForAnnualIncome;
        private MonetaryAmount taxBaseForAnnualBonus;
        private MonetaryAmount taxAmountForAnnualBonus;

        public Builder taxBaseForAnnualIncome(Number taxBaseForAnnualIncome) {
            this.taxBaseForAnnualIncome = MoneyUtil.toAmount(taxBaseForAnnualIncome);
            return this;
        }

        public Builder taxBaseForAnnualIncome(MonetaryAmount taxBaseForAnnualIncome) {
            this.taxBaseForAnnualIncome = taxBaseForAnnualIncome;
            return this;
        }

        public Builder taxAmountForAnnualIncome(Number taxAmountForAnnualIncome) {
            this.taxAmountForAnnualIncome = MoneyUtil.toAmount(taxAmountForAnnualIncome);
            return this;
        }

        public Builder taxAmountForAnnualIncome(MonetaryAmount taxAmountForAnnualIncome) {
            this.taxAmountForAnnualIncome = taxAmountForAnnualIncome;
            return this;
        }

        public Builder taxBaseForAnnualBonus(Number taxBaseForAnnualBonus) {
            this.taxBaseForAnnualBonus = MoneyUtil.toAmount(taxBaseForAnnualBonus);
            return this;
        }

        public Builder taxBaseForAnnualBonus(MonetaryAmount taxBaseForAnnualBonus) {
            this.taxBaseForAnnualBonus = taxBaseForAnnualBonus;
            return this;
        }

        public Builder taxAmountForAnnualBonus(Number taxAmountForAnnualBonus) {
            this.taxAmountForAnnualBonus = MoneyUtil.toAmount(taxAmountForAnnualBonus);
            return this;
        }

        public Builder taxAmountForAnnualBonus(MonetaryAmount taxAmountForAnnualBonus) {
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
