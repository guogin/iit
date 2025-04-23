package com.yahaha.iit.calc;

import javax.money.MonetaryAmount;

public class AnnualOneTimeBonusAssessor implements TaxableIncomeAssessor {
    @Override
    public TraceableAmount determineTaxableAmount(IITRequest request) {
        MonetaryAmount taxableAnnualOneTimeBonus = request.getAnnualOneTimeBonus();
        DiagnosticMessage message = new DiagnosticMessage("全年一次性奖金: {0}", taxableAnnualOneTimeBonus);
        TraceLog traceLog = TraceLog.builder().footerMessage(message).build();
        return new TraceableAmount(taxableAnnualOneTimeBonus, traceLog);
    }
}
