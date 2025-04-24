package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;

import javax.money.MonetaryAmount;
import java.util.HashMap;
import java.util.Map;

public class IITCalculatorImpl2024 implements IITCalculator {
    @Override
    public IITResult calculate(IITRequest request) {
        IITResult result = new IITResult();

        MonetaryAmount taxableAnnualIncome = determineTaxableAnnualComprehensiveIncome(request);
        ProgressiveTax annualComprehensiveIncomeTax = new AnnualComprehensiveIncomeTax();
        MonetaryAmount taxAmountForAnnualIncome = annualComprehensiveIncomeTax.calculate(taxableAnnualIncome).getAmount();
        result.addItem(TaxItem.builder().taxBaseAmount(taxableAnnualIncome).taxAmount(taxAmountForAnnualIncome).build());

        if (request.getBonusTaxationOption() == BonusTaxationOption.ONE_TIME_TAXATION) {
            MonetaryAmount taxableAnnualBonus = request.getAnnualOneTimeBonus();
            ProgressiveTax annualOneTimeBonusTax = new AnnualOneTimeBonusTax();
            MonetaryAmount taxAmountForAnnualBonus = annualOneTimeBonusTax.calculate(taxableAnnualBonus).getAmount();
            result.addItem(TaxItem.builder().taxBaseAmount(taxableAnnualBonus).taxAmount(taxAmountForAnnualBonus).build());
        }

        return result;
    }

    private MonetaryAmount determineTaxableAnnualComprehensiveIncome(IITRequest request) {
        //费用
        MonetaryAmount expenses = request.getServiceRemuneration()
                .add(request.getAuthorsRemuneration())
                .add(request.getRoyaltyFees())
                .multiply(0.2); // 20%

        //稿酬所得免税部分
        MonetaryAmount exemptionFromAuthorsRemuneration = request.getAuthorsRemuneration()
                .multiply(0.8)  // 1 - 20%
                .multiply(0.3);  // 30%

        // 减除费用
        MonetaryAmount deductibleExpenses = MoneyUtil.toAmount(60000);

        Map<String, MonetaryAmount> additions = new HashMap<>();
        Map<String, MonetaryAmount> deductions = new HashMap<>();

        additions.put("年度工资收入", request.getAnnualWageIncome());
        additions.put("劳务报酬", request.getServiceRemuneration());
        additions.put("稿酬", request.getAuthorsRemuneration());
        additions.put("特许权使用费", request.getRoyaltyFees());

        if (request.getBonusTaxationOption() == BonusTaxationOption.INTEGRATED_TAXATION) {
            additions.put("全年一次性奖金", request.getAnnualOneTimeBonus());
        }

        deductions.put("费用", expenses);
        deductions.put("稿酬所得免税部分", exemptionFromAuthorsRemuneration);
        deductions.put("减除费用", deductibleExpenses);
        deductions.put("专项附加扣除", request.getAdditionalSpecialDeductions());
        deductions.put("其他扣除项目", request.getOtherDeductions());

        Map<String, MonetaryAmount> detailItems = new HashMap<>();

        additions.forEach((k, v) -> detailItems.put(k, v));
        deductions.forEach((k, v) -> detailItems.put(k, v.negate()));

        MonetaryAmount taxableAnnualComprehensiveIncome = detailItems.values().stream()
                .reduce(MoneyUtil.ZERO, MonetaryAmount::add);
        return taxableAnnualComprehensiveIncome.isGreaterThan(MoneyUtil.ZERO)?
                taxableAnnualComprehensiveIncome : MoneyUtil.ZERO;
    }
}
