package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;

import javax.money.MonetaryAmount;

public class AnnualOneTimeBonusAssessor implements TaxableIncomeAssessor {
    @Override
    public TraceableTaxBaseAmount determineTaxableAmount(IITRequest request) {
        MonetaryAmount taxableAnnualOneTimeBonus = request.getAnnualOneTimeBonus();
        TraceLog traceLog = TraceLog.builder()
                .headerMessage(new DiagnosticMessage("计算全年一次性奖金应纳税部分"))
                .footerMessage(new DiagnosticMessage("全年一次性奖金应纳税部分: {0}", MoneyUtil.format(taxableAnnualOneTimeBonus)))
                .build();
        return new TraceableTaxBaseAmount(taxableAnnualOneTimeBonus, traceLog);
    }
}
