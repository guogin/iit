package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public abstract class AbstractProgressiveTax implements ProgressiveTax {
    protected static final MonetaryAmount INFINITE_AMOUNT = MoneyUtil.toAmount(BigDecimal.valueOf(Double.MAX_VALUE));

    @Override
    public TraceableTaxCalculationResultItem calculate(MonetaryAmount taxBaseAmount) {
        UnaryOperator<MonetaryAmount> allocator = getIncomeAllocationFunction();
        MonetaryAmount allocatedTaxBaseAmount = allocator.apply(taxBaseAmount);

        Predicate<ProgressiveTaxBracket> isInBracket = b -> b.getFromAmount().isLessThan(allocatedTaxBaseAmount) && b.getToAmount().isGreaterThanOrEqualTo(allocatedTaxBaseAmount);
        Predicate<ProgressiveTaxBracket> isZero = b -> b.getFromAmount().isZero() && allocatedTaxBaseAmount.isZero();

        ProgressiveTaxBracket bracket = brackets.stream()
                .filter(isZero.or(isInBracket))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No tax bracket found for the given amount: " + allocatedTaxBaseAmount));

        MonetaryAmount taxAmount = taxBaseAmount.multiply(bracket.getTaxRate())
                .subtract(bracket.getRapidCalculationDeduction());

        TraceLog traceLog = buildTraceLog(taxAmount, bracket);

        return new TraceableTaxCalculationResultItem(taxBaseAmount, taxAmount, bracket.getTaxRate(), traceLog);
    }

    private TraceLog buildTraceLog(MonetaryAmount taxAmount, ProgressiveTaxBracket bracket) {
        List<DiagnosticMessage> diagnosticMessages = new ArrayList<>();
        int index = brackets.indexOf(bracket) + 1;
        String taxRateInPercentage = MoneyUtil.formatPercentage(bracket.getTaxRate());
        diagnosticMessages.add(new DiagnosticMessage("通过查询税率表，应使用第{0}档税率，税率为{1}", index, taxRateInPercentage));
        diagnosticMessages.add(new DiagnosticMessage("计算税额时，扣除速算扣除数{0}", bracket.getRapidCalculationDeduction()));
        diagnosticMessages.add(new DiagnosticMessage("最终税额为{0}", MoneyUtil.format(taxAmount)));

        return TraceLog.builder().bodyMessages(diagnosticMessages).build();
    }

    private ArrayList<ProgressiveTaxBracket> brackets = new ArrayList<>();

    /**
     * In complex case where you need to allocate income before you find out the tax bracket,
     * you can override this method to provide the allocation function.
     * @return a unary function which takes an annual income as input, and returns the amount for tax bracket calculation
     */
    protected UnaryOperator<MonetaryAmount> getIncomeAllocationFunction() {
        return UnaryOperator.identity();
    }

    public AbstractProgressiveTax() {
        brackets = defineTaxBrackets();
        brackets.sort((o1, o2) -> o1.getToAmount().compareTo(o2.getToAmount()));
    }

    protected abstract ArrayList<ProgressiveTaxBracket> defineTaxBrackets();
}
