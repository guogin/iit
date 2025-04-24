package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;

import java.math.BigDecimal;
import java.util.Arrays;

public class TaxRoutineImpl implements TaxRoutine {
    private String name;
    private TaxableIncomeAssessor taxBaseAssessor;
    private ProgressiveTax taxCalculator;

    public TaxRoutineImpl(String name, TaxableIncomeAssessor taxBaseAssessor, ProgressiveTax taxCalculator) {
        this.name = name;
        this.taxBaseAssessor = taxBaseAssessor;
        this.taxCalculator = taxCalculator;
    }

    @Override
    public TaxItem execute(IITRequest request) {
        // Always follow the same routine: Determine base, and then calculate tax
        TraceableAmount taxBase = taxBaseAssessor.determineTaxableAmount(request);
        TraceableAmount taxAmount = taxCalculator.calculate(taxBase.getAmount());

        TraceLog traceLog = TraceLog.builder()
                .headerMessage(new DiagnosticMessage("计算{0}的税额", name))
                .subTraceLogs(Arrays.asList(taxBase.getTraceLog(), taxAmount.getTraceLog()))
                .footerMessage(new DiagnosticMessage("{0}的税额：{1}", name, MoneyUtil.format(taxAmount.getAmount())))
                .build();

        BigDecimal taxAmountDecimalNumber = taxAmount.getAmount().getNumber().numberValueExact(BigDecimal.class);
        BigDecimal taxBaseDecimalNumber = taxBase.getAmount().getNumber().numberValueExact(BigDecimal.class);
        BigDecimal taxRate = BigDecimal.ZERO;
        if (taxBaseDecimalNumber.compareTo(BigDecimal.ZERO) != 0) {
            taxRate = taxAmountDecimalNumber.divide(taxBaseDecimalNumber, 6, BigDecimal.ROUND_HALF_UP);
        }

        return TaxItem.builder()
                .taxBaseAmount(taxBase.getAmount())
                .taxAmount(taxAmount.getAmount())
                .taxRate(taxRate)
                .traceLog(traceLog)
                .build();
    }
}
