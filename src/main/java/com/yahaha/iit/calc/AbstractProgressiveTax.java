package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public abstract class AbstractProgressiveTax implements ProgressiveTax {
    protected static final MonetaryAmount INFINITE_AMOUNT = MoneyUtil.toAmount(BigDecimal.valueOf(Double.MAX_VALUE));

    @Override
    public MonetaryAmount calculate(MonetaryAmount taxBaseAmount) {
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

        return taxAmount;
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
