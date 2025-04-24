package com.yahaha.iit.calc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.money.MonetaryAmount;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class TraceableTaxBaseAmount {
    private MonetaryAmount amount;
    @JsonIgnore
    private TraceLog traceLog;
}
