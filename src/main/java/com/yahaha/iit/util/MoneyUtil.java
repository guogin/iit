package com.yahaha.iit.util;

import org.javamoney.moneta.format.AmountFormatParams;
import org.javamoney.moneta.format.CurrencyStyle;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.util.Locale;

public class MoneyUtil {
    public static final CurrencyUnit CNY = Monetary.getCurrency("CNY");
    public static final MonetaryAmount ZERO = Monetary.getDefaultAmountFactory().setCurrency(CNY).setNumber(0).create();

    private static MonetaryAmountFormat fmt = MonetaryFormats.getAmountFormat(
            AmountFormatQueryBuilder.of(Locale.CHINA)
                    .set(CurrencyStyle.SYMBOL)
                    .set(AmountFormatParams.PATTERN, "¤#,##0.00")
                    .build());
    private static NumberFormat percentageFmt = NumberFormat.getPercentInstance(Locale.CHINA);

    public static MonetaryAmount toAmount(Number amount) {
        return amount == null ? ZERO :
                Monetary.getDefaultAmountFactory().setCurrency(CNY).setNumber(amount).create();
    }

    public static String format(MonetaryAmount amount) {
        return fmt.format(amount);
    }

    public static String formatPercentage(Number number) {
        return percentageFmt.format(number);
    }
}
