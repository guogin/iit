package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public abstract class AbstractProgressiveTaxCalculator implements TaxCalculator {
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

        TraceLog traceLog = buildTraceLog(taxBaseAmount, taxAmount, bracket);

        return new TraceableTaxCalculationResultItem(taxBaseAmount, taxAmount, bracket.getTaxRate(), traceLog);
    }

    private TraceLog buildTraceLog(MonetaryAmount taxBaseAmount, MonetaryAmount taxAmount, ProgressiveTaxBracket bracket) {
        Map<String, MonetaryAmount> diagnostics = new HashMap<>();
        diagnostics.put("税基 x 税率 =", taxBaseAmount.multiply(bracket.getTaxRate()));
        diagnostics.put("扣除速算扣除数", bracket.getRapidCalculationDeduction());
        diagnostics.put("最终税额", taxAmount);

        return TraceLog.builder()
                .headerMessage(new DiagnosticMessage("通过查询税率表，应使用第{0}档税率，税率为{1}", brackets.indexOf(bracket) + 1, MoneyUtil.formatPercentage(bracket.getTaxRate())))
                .body(diagnostics)
                .footerMessage(new DiagnosticMessage("应纳税额: {0}", MoneyUtil.format(taxAmount)))
                .build();
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

    public AbstractProgressiveTaxCalculator() {
        brackets = defineTaxBrackets();
        brackets.sort((o1, o2) -> o1.getToAmount().compareTo(o2.getToAmount()));
    }

    protected abstract ArrayList<ProgressiveTaxBracket> defineTaxBrackets();
}
