package com.yahaha.iit.calc;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IITRequest {
    // Incomes
    private BigDecimal annualWageIncome; //年度工资收入
    private BigDecimal annualOneTimeBonus; //全年一次性奖金
    private BigDecimal serviceRemuneration; //劳务报酬
    private BigDecimal royaltyFees; //特许权使用费
    private BigDecimal authorsRemuneration; //稿酬

    // Deductions
    private BigDecimal specialDeductions; //专项扣除（三险一金）
    private BigDecimal additionalSpecialDeductions; //专项附加扣除（赡养老人、房屋贷款、子女教育）
    private BigDecimal otherDeductions; //其他扣除项目
}
