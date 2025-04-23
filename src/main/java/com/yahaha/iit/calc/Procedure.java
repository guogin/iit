package com.yahaha.iit.calc;

import javax.money.MonetaryAmount;

public interface Procedure {
    MonetaryAmount execute();
    TraceLog explain();
}
