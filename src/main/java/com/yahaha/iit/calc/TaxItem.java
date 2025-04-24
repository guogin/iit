package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;

import static com.yahaha.iit.util.MoneyUtil.ZERO;

@Builder
@EqualsAndHashCode
@ToString
public class TaxItem {
    private MonetaryAmount taxBaseAmount;
    private MonetaryAmount taxAmount;
    private BigDecimal taxRate;
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
