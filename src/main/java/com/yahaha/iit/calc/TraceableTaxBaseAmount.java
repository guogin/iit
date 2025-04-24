package com.yahaha.iit.calc;

import lombok.*;

import javax.money.MonetaryAmount;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class TraceableTaxBaseAmount {
    private MonetaryAmount amount;
    private TraceLog traceLog;
}
