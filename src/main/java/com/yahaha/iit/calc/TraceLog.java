package com.yahaha.iit.calc;

import lombok.*;

import java.util.List;

/**
 * TraceLog is a hierarchical structure: </br>
 * TraceLog: </br>
 *      + headerMessage (optional) </br>
 *      + body (optional) </br>
 *      |--------+ bodyMessage1 </br>
 *      |--------+ bodyMessage2 </br>
 *      |--------+ bodyMessage3 </br>
 *      Or </br>
 *      |--------+ subTraceLog1 </br>
 *      |--------+ subTraceLog2 </br>
 *      |--------+ subTraceLog3 </br>
 *      + footerMessage (optional) </br>
 */
@Getter
@EqualsAndHashCode
@ToString
@Builder
public class TraceLog {
    DiagnosticMessage headerMessage;
    List<DiagnosticMessage> bodyMessages;
    List<TraceLog> subTraceLogs;
    DiagnosticMessage footerMessage;

    public void traverse(TraceLogVisitor visitor) {
        visitor.start();
        if (headerMessage != null) {
            visitor.visitHeader(headerMessage);
        }
        if(subTraceLogs != null) {
            visitor.visitSubNodes(subTraceLogs);
        } else if (bodyMessages != null) {
            visitor.visitBody(bodyMessages);
        }
        if (footerMessage != null) {
            visitor.visitFooter(footerMessage);
        }
        visitor.end();
    }
}
