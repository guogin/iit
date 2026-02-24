package com.yahaha.iit.calc;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.money.MonetaryAmount;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class TraceItem {
    private final String label;
    private final MonetaryAmount amount;
}
