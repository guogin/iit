package com.yahaha.iit.calc;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class ProgressiveTaxBracket {
    private final MonetaryAmount fromAmount;
    private final MonetaryAmount toAmount;
    private final BigDecimal taxRate;
    private final MonetaryAmount rapidCalculationDeduction;
}
