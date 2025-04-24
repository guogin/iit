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
    private List<TraceableTaxCalculationResultItem> items;
    private TraceLog traceLog;

    private IITResult() {
    }

    public static IITResult of(TraceableTaxCalculationResultItem... items) {
        IITResult result = new IITResult();
        result.items = Arrays.asList(items);
        result.traceLog = TraceLog.builder()
                .subTraceLogs(result.items.stream().map(TraceableTaxCalculationResultItem::getTraceLog).collect(Collectors.toList()))
                .build();
        return result;
    }

    public MonetaryAmount getTotalTaxAmount() {
        return items.stream().map(TraceableTaxCalculationResultItem::getTaxAmount).reduce(ZERO, MonetaryAmount::add);
    }
}
