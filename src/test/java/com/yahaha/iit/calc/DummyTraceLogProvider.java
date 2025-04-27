package com.yahaha.iit.calc;

import com.yahaha.iit.util.MoneyUtil;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yahaha.iit.util.MoneyUtil.ZERO;

class DummyTraceLogProvider {
    static TraceLog createTraceLog11() {
        DiagnosticMessage header11 = new DiagnosticMessage("1-1");
        Map<String, MonetaryAmount> body11 = new HashMap<>();
        body11.put("1-1-1", ZERO);
        body11.put("1-1-2", ZERO);
        body11.put("1-1-3", ZERO);
        DiagnosticMessage footer11 = new DiagnosticMessage("1-1");
        TraceLog traceLog11 = TraceLog.builder().headerMessage(header11).body(body11).footerMessage(footer11).build();

        return traceLog11;
    }

    static TraceLog createTraceLog12() {
        DiagnosticMessage header12 = new DiagnosticMessage("1-2");
        Map<String, MonetaryAmount> body12 = new HashMap<>();
        body12.put("1-2-1", ZERO);
        body12.put("1-2-2", ZERO);
        body12.put("1-2-3", ZERO);
        body12.put("1-2-4", ZERO);
        DiagnosticMessage footer12 = new DiagnosticMessage("1-2");
        TraceLog traceLog12 = TraceLog.builder().headerMessage(header12).body(body12).footerMessage(footer12).build();

        return traceLog12;
    }

    static TraceLog createTraceLog13() {
        DiagnosticMessage header131 = new DiagnosticMessage("1-3-1");
        DiagnosticMessage footer131 = new DiagnosticMessage("1-3-1");
        TraceLog traceLog131 = TraceLog.builder().headerMessage(header131).footerMessage(footer131).build();

        DiagnosticMessage header132 = new DiagnosticMessage("1-3-2");
        Map<String, MonetaryAmount> body132 = new HashMap<>();
        body132.put("1-3-2-1", ZERO);
        body132.put("1-3-2-2", ZERO);
        DiagnosticMessage footer132 = new DiagnosticMessage("1-3-2");
        TraceLog traceLog132 = TraceLog.builder().headerMessage(header132).body(body132).footerMessage(footer132).build();

        DiagnosticMessage header13 = new DiagnosticMessage("1-3");
        List<TraceLog> subLog13 = new ArrayList<>();
        subLog13.add(traceLog131);
        subLog13.add(traceLog132);
        DiagnosticMessage footer13 = new DiagnosticMessage("1-3");
        TraceLog traceLog13 = TraceLog.builder().headerMessage(header13).subTraceLogs(subLog13).footerMessage(footer13).build();

        return traceLog13;
    }
}
