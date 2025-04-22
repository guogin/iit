package com.yahaha.iit.calc;

import lombok.*;

import javax.money.MonetaryAmount;
import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class TraceableAmount {
    private MonetaryAmount amount;
    private List<DiagnosticMessage> traceLogs;
}
