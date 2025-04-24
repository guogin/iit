package com.yahaha.iit.calc;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.money.MonetaryAmount;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.yahaha.iit.util.MoneyUtil.ZERO;

@Getter
@EqualsAndHashCode
@ToString
public class IITResult {
    private List<TaxItem> items;
    private TraceLog traceLog;

    private IITResult() {
    }

    public static IITResult of(TaxItem... items) {
        IITResult result = new IITResult();
        result.items = Arrays.asList(items);
        result.traceLog = TraceLog.builder()
                .subTraceLogs(result.items.stream().map(TaxItem::getTraceLog).collect(Collectors.toList()))
                .build();
        return result;
    }

    public static IITResult create(List<TaxItem> items, TraceLog traceLog) {
        IITResult result = new IITResult();
        result.items = items;
        result.traceLog = traceLog;
        return result;
    }

    public MonetaryAmount getTotalTaxAmount() {
        return items.stream().map(TaxItem::getTaxAmount).reduce(ZERO, MonetaryAmount::add);
    }
}
