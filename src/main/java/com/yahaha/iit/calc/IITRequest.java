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
    private MonetaryAmount annualWageIncome; //年度工资收入
    private MonetaryAmount annualOneTimeBonus; //全年一次性奖金
    private MonetaryAmount serviceRemuneration; //劳务报酬
    private MonetaryAmount royaltyFees; //特许权使用费
    private MonetaryAmount authorsRemuneration; //稿酬

    // Deductions
    private MonetaryAmount specialDeductions; //专项扣除（三险一金）
    private MonetaryAmount additionalSpecialDeductions; //专项附加扣除（赡养老人、房屋贷款、子女教育）
    private MonetaryAmount otherDeductions; //其他扣除项目

    // Other
    private BonusTaxationOption bonusTaxationOption;

    /**
     * @param annualWageIncome 年度工资收入
     */
    public void setAnnualWageIncome(Number annualWageIncome) {
        this.annualWageIncome = MoneyUtil.toAmount(annualWageIncome);
    }

    /**
     * @param annualOneTimeBonus 全年一次性奖金
     */
    public void setAnnualOneTimeBonus(Number annualOneTimeBonus) {
        this.annualOneTimeBonus = MoneyUtil.toAmount(annualOneTimeBonus);
    }

    /**
     * @param serviceRemuneration 劳务报酬
     */
    public void setServiceRemuneration(Number serviceRemuneration) {
        this.serviceRemuneration = MoneyUtil.toAmount(serviceRemuneration);
    }

    /**
     * @param royaltyFees 特许权使用费
     */
    public void setRoyaltyFees(Number royaltyFees) {
        this.royaltyFees = MoneyUtil.toAmount(royaltyFees);
    }

    /**
     * @param authorsRemuneration 稿酬
     */
    public void setAuthorsRemuneration(Number authorsRemuneration) {
        this.authorsRemuneration = MoneyUtil.toAmount(authorsRemuneration);
    }

    /**
     * @param specialDeductions 专项扣除（三险一金）
     */
    public void setSpecialDeductions(Number specialDeductions) {
        this.specialDeductions = MoneyUtil.toAmount(specialDeductions);
    }

    /**
     * @param additionalSpecialDeductions 专项附加扣除（赡养老人、房屋贷款、子女教育）
     */
    public void setAdditionalSpecialDeductions(Number additionalSpecialDeductions) {
        this.additionalSpecialDeductions = MoneyUtil.toAmount(additionalSpecialDeductions);
    }

    /**
     * @param otherDeductions 其他扣除项目
     */
    public void setOtherDeductions(Number otherDeductions) {
        this.otherDeductions = MoneyUtil.toAmount(otherDeductions);
    }

    /**
     * @param bonusTaxationOption 奖金计税方式
     */
    public void setBonusTaxationOption(BonusTaxationOption bonusTaxationOption) {
        this.bonusTaxationOption = bonusTaxationOption;
    }

    /**
     * @return 年度工资收入
     */
    public MonetaryAmount getAnnualWageIncome() {
        return annualWageIncome == null? ZERO : annualWageIncome;
    }

    /**
     * @return 全年一次性奖金
     */
    public MonetaryAmount getAnnualOneTimeBonus() {
        return annualOneTimeBonus == null? ZERO : annualOneTimeBonus;
    }

    /**
     * @return 劳务报酬
     */
    public MonetaryAmount getServiceRemuneration() {
        return serviceRemuneration == null? ZERO : serviceRemuneration;
    }

    /**
     * @return 特许权使用费
     */
    public MonetaryAmount getRoyaltyFees() {
        return royaltyFees == null? ZERO : royaltyFees;
    }

    /**
     * @return 稿酬
     */
    public MonetaryAmount getAuthorsRemuneration() {
        return authorsRemuneration == null? ZERO : authorsRemuneration;
    }

    /**
     * @return 专项扣除（三险一金）
     */
    public MonetaryAmount getSpecialDeductions() {
        return specialDeductions == null? ZERO : specialDeductions;
    }

    /**
     * @return 专项附加扣除（赡养老人、房屋贷款、子女教育）
     */
    public MonetaryAmount getAdditionalSpecialDeductions() {
        return additionalSpecialDeductions == null? ZERO : additionalSpecialDeductions;
    }

    /**
     * @return 其他扣除项目
     */
    public MonetaryAmount getOtherDeductions() {
        return otherDeductions == null? ZERO : otherDeductions;
    }

    /**
     * @return 奖金计税方式
     */
    public BonusTaxationOption getBonusTaxationOption() {
        return bonusTaxationOption == null? BonusTaxationOption.ONE_TIME_TAXATION : bonusTaxationOption;
    }
}
