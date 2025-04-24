package com.yahaha.iit.calc;

import lombok.Data;

import javax.money.MonetaryAmount;
import java.util.List;

import static com.yahaha.iit.util.MoneyUtil.ZERO;

@Data
public class IITResult {
    private List<TaxItem> items;
    private TraceLog traceLog;

    public MonetaryAmount getTotalTaxAmount() {
        return items.stream().map(TaxItem::getTaxAmount).reduce(ZERO, MonetaryAmount::add);
    }
}
