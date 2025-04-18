package com.yahaha.iit.calc;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.yahaha.iit.util.MoneyUtil.toAmount;

public class AnnualComprehensiveIncomeTax extends AbstractProgressiveTax {

    public AnnualComprehensiveIncomeTax() {
        super();
    }

    @Override
    protected ArrayList<ProgressiveTaxBracket> defineTaxBrackets() {
        ArrayList<ProgressiveTaxBracket> brackets = new ArrayList<>();
        brackets.add(new ProgressiveTaxBracket(toAmount(0), toAmount(36000), BigDecimal.valueOf(0.03), toAmount(0)));
        brackets.add(new ProgressiveTaxBracket(toAmount(36000), toAmount(144000), BigDecimal.valueOf(0.1), toAmount(2520)));
        brackets.add(new ProgressiveTaxBracket(toAmount(144000), toAmount(300000), BigDecimal.valueOf(0.2), toAmount(16920)));
        brackets.add(new ProgressiveTaxBracket(toAmount(300000), toAmount(420000), BigDecimal.valueOf(0.25), toAmount(31920)));
        brackets.add(new ProgressiveTaxBracket(toAmount(420000), toAmount(660000), BigDecimal.valueOf(0.3), toAmount(52920)));
        brackets.add(new ProgressiveTaxBracket(toAmount(660000), toAmount(960000), BigDecimal.valueOf(0.35), toAmount(85920)));
        brackets.add(new ProgressiveTaxBracket(toAmount(960000), INFINITE_AMOUNT, BigDecimal.valueOf(0.45), toAmount(181920)));
        return brackets;
    }
}
