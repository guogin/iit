package com.yahaha.iit.calc;

import javax.money.MonetaryAmount;
import java.util.List;

public interface Procedure {
    MonetaryAmount execute();
    List<DiagnosticMessage> explain();
}
