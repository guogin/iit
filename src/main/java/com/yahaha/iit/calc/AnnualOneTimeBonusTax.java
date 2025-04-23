package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.function.UnaryOperator;

import static com.yahaha.iit.util.MoneyUtil.toAmount;

/**
 * @Reference <a href="https://www.gov.cn/zhengce/zhengceku/202308/content_6900595.htm">财政部 税务总局公告2023年第30号</a>
 */
public class AnnualOneTimeBonusTax extends AbstractProgressiveTax {
    public AnnualOneTimeBonusTax() {
        super();
    }

    @Override
    protected UnaryOperator<MonetaryAmount> getIncomeAllocationFunction() {
        return income -> income.divide(12);
    }

    @Override
    protected ArrayList<ProgressiveTaxBracket> defineTaxBrackets() {
        ArrayList<ProgressiveTaxBracket> brackets = new ArrayList<>();
        brackets.add(new ProgressiveTaxBracket(toAmount(0), toAmount(3000), BigDecimal.valueOf(0.03), toAmount(0)));
        brackets.add(new ProgressiveTaxBracket(toAmount(3000), toAmount(12000), BigDecimal.valueOf(0.1), toAmount(210)));
        brackets.add(new ProgressiveTaxBracket(toAmount(12000), toAmount(25000), BigDecimal.valueOf(0.2), toAmount(1410)));
        brackets.add(new ProgressiveTaxBracket(toAmount(25000), toAmount(35000), BigDecimal.valueOf(0.25), toAmount(2660)));
        brackets.add(new ProgressiveTaxBracket(toAmount(35000), toAmount(55000), BigDecimal.valueOf(0.3), toAmount(4410)));
        brackets.add(new ProgressiveTaxBracket(toAmount(55000), toAmount(80000), BigDecimal.valueOf(0.35), toAmount(7160)));
        brackets.add(new ProgressiveTaxBracket(toAmount(80000), INFINITE_AMOUNT, BigDecimal.valueOf(0.45), toAmount(15160)));
        return brackets;
    }
}
