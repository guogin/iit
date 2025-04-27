package com.yahaha.iit.calc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.money.MonetaryAmount;
import java.util.List;
import java.util.Map;

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
    @JsonProperty("header")
    DiagnosticMessage headerMessage;
    @JsonProperty("body")
    Map<String, MonetaryAmount> body;
    @JsonProperty("subLogs")
    List<TraceLog> subTraceLogs;
    @JsonProperty("footer")
    DiagnosticMessage footerMessage;
}
