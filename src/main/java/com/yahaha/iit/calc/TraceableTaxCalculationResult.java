package com.yahaha.iit.calc;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class TraceableTaxCalculationResult {
    private List<TraceableTaxCalculationResultItem> items;
    @JsonIgnore
    private TraceLog traceLog;

    private TraceableTaxCalculationResult() {
    }

    public static TraceableTaxCalculationResult of(TraceableTaxCalculationResultItem... items) {
        TraceableTaxCalculationResult result = new TraceableTaxCalculationResult();
        result.items = Arrays.asList(items);
        result.traceLog = TraceLog.builder()
                .subTraceLogs(result.items.stream().map(TraceableTaxCalculationResultItem::getTraceLog).collect(Collectors.toList()))
                .build();
        return result;
    }

    @JsonGetter
    public MonetaryAmount getTotalTaxAmount() {
        return items.stream().map(TraceableTaxCalculationResultItem::getTaxAmount).reduce(ZERO, MonetaryAmount::add);
    }
}
