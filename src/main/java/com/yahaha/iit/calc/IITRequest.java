package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.money.MonetaryAmount;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class IITRequest {
    public static final MonetaryAmount ZERO = MoneyUtil.ZERO;

    // Incomes
    private MonetaryAmount annualWageIncome;
    private MonetaryAmount annualOneTimeBonus;
    private MonetaryAmount serviceRemuneration;
    private MonetaryAmount royaltyFees;
    private MonetaryAmount authorsRemuneration;

    // Deductions
    private MonetaryAmount specialDeductionsForSocialInsuranceAndHousingFund;
    private MonetaryAmount additionalSpecialDeductions;
    private MonetaryAmount otherDeductions;

    public void setAnnualWageIncome(Number annualWageIncome) {
        this.annualWageIncome = MoneyUtil.toAmount(annualWageIncome);
    }

    public void setAnnualOneTimeBonus(Number annualOneTimeBonus) {
        this.annualOneTimeBonus = MoneyUtil.toAmount(annualOneTimeBonus);
    }

    public void setServiceRemuneration(Number serviceRemuneration) {
        this.serviceRemuneration = MoneyUtil.toAmount(serviceRemuneration);
    }

    public void setRoyaltyFees(Number royaltyFees) {
        this.royaltyFees = MoneyUtil.toAmount(royaltyFees);
    }

    public void setAuthorsRemuneration(Number authorsRemuneration) {
        this.authorsRemuneration = MoneyUtil.toAmount(authorsRemuneration);
    }

    public void setSpecialDeductionsForSocialInsuranceAndHousingFund(Number specialDeductionsForSocialInsuranceAndHousingFund) {
        this.specialDeductionsForSocialInsuranceAndHousingFund = MoneyUtil.toAmount(specialDeductionsForSocialInsuranceAndHousingFund);
    }

    public void setAdditionalSpecialDeductions(Number additionalSpecialDeductions) {
        this.additionalSpecialDeductions = MoneyUtil.toAmount(additionalSpecialDeductions);
    }

    public void setOtherDeductions(Number otherDeductions) {
        this.otherDeductions = MoneyUtil.toAmount(otherDeductions);
    }

    public MonetaryAmount getAnnualWageIncome() {
        return annualWageIncome == null? ZERO : annualWageIncome;
    }

    public MonetaryAmount getAnnualOneTimeBonus() {
        return annualOneTimeBonus == null? ZERO : annualOneTimeBonus;
    }

    public MonetaryAmount getServiceRemuneration() {
        return serviceRemuneration == null? ZERO : serviceRemuneration;
    }

    public MonetaryAmount getRoyaltyFees() {
        return royaltyFees == null? ZERO : royaltyFees;
    }

    public MonetaryAmount getAuthorsRemuneration() {
        return authorsRemuneration == null? ZERO : authorsRemuneration;
    }

    public MonetaryAmount getSpecialDeductionsForSocialInsuranceAndHousingFund() {
        return specialDeductionsForSocialInsuranceAndHousingFund == null? ZERO : specialDeductionsForSocialInsuranceAndHousingFund;
    }

    public MonetaryAmount getAdditionalSpecialDeductions() {
        return additionalSpecialDeductions == null? ZERO : additionalSpecialDeductions;
    }

    public MonetaryAmount getOtherDeductions() {
        return otherDeductions == null? ZERO : otherDeductions;
    }
}
