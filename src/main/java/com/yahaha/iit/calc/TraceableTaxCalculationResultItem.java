package com.yahaha.iit.calc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;

import static com.yahaha.iit.util.MoneyUtil.ZERO;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class TraceableTaxCalculationResultItem {
    private MonetaryAmount taxBaseAmount;
    private MonetaryAmount taxAmount;
    private BigDecimal taxRate;
    @JsonIgnore
    private TraceLog traceLog;

    public MonetaryAmount getTaxBaseAmount() {
        return taxBaseAmount == null ? ZERO : taxBaseAmount;
    }

    public MonetaryAmount getTaxAmount() {
        return taxAmount == null ? ZERO : taxAmount;
    }

    public BigDecimal getTaxRate() {
        return taxRate == null ? BigDecimal.ZERO : taxRate;
    }

    public TraceLog getTraceLog() {
        return traceLog;
    }
}
