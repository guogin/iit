package com.yahaha.iit.calc;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.List;

import static com.yahaha.iit.util.MoneyUtil.ZERO;

@EqualsAndHashCode
@ToString
public class IITResult {
    private List<TaxItem> items = new ArrayList<>();

    public IITResult() {
    }

    public void addItem(TaxItem item) {
        items.add(item);
    }

    public static IITResult of(TaxItem... items) {
        IITResult result = new IITResult();
        for (TaxItem item : items) {
            result.items.add(item);
        }
        return result;
    }

    public MonetaryAmount getTotalTaxAmount() {
        return items.stream().map(TaxItem::getTaxAmount).reduce(ZERO, MonetaryAmount::add);
    }
}
