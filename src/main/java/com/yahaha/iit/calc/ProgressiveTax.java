package com.yahaha.iit.calc;

import javax.money.MonetaryAmount;

public interface ProgressiveTax {
    TraceableAmount calculate(MonetaryAmount taxBaseAmount);
}
