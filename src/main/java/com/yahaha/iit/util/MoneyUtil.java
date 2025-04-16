package com.yahaha.iit.util;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

public class MoneyUtil {
    public static final CurrencyUnit CNY = Monetary.getCurrency("CNY");
    public static final MonetaryAmount ZERO = Monetary.getDefaultAmountFactory().setCurrency(CNY).setNumber(0).create();


    public static MonetaryAmount toAmount(Number amount) {
        return amount == null? ZERO:
                Monetary.getDefaultAmountFactory().setCurrency(CNY).setNumber(amount).create();
    }
}
