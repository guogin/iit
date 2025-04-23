package com.yahaha.iit.calc;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TraceLogJsonConverterTest {
    private TraceLog traceLog;
    private TraceLogJsonConverter traceLogJsonConverter;

    @BeforeEach
    void setUp() {
        DiagnosticMessage header11 = new DiagnosticMessage("1-1");
        List<DiagnosticMessage> body11 = new ArrayList<>();
        body11.add(new DiagnosticMessage("1-1-1"));
        body11.add(new DiagnosticMessage("1-1-2"));
        body11.add(new DiagnosticMessage("1-1-3"));
        DiagnosticMessage footer11 = new DiagnosticMessage("1-1");
        TraceLog traceLog11 = TraceLog.builder().headerMessage(header11).bodyMessages(body11).footerMessage(footer11).build();

        DiagnosticMessage header12 = new DiagnosticMessage("1-2");
        List<DiagnosticMessage> body12 = new ArrayList<>();
        body12.add(new DiagnosticMessage("1-2-1"));
        body12.add(new DiagnosticMessage("1-2-2"));
        body12.add(new DiagnosticMessage("1-2-3"));
        body12.add(new DiagnosticMessage("1-2-4"));
        DiagnosticMessage footer12 = new DiagnosticMessage("1-2");
        TraceLog traceLog12 = TraceLog.builder().headerMessage(header12).bodyMessages(body12).footerMessage(footer12).build();

        DiagnosticMessage header131 = new DiagnosticMessage("1-3-1");
        DiagnosticMessage footer131 = new DiagnosticMessage("1-3-1");
        TraceLog traceLog131 = TraceLog.builder().headerMessage(header131).footerMessage(footer131).build();

        DiagnosticMessage header132 = new DiagnosticMessage("1-3-2");
        List<DiagnosticMessage> body132 = new ArrayList<>();
        body132.add(new DiagnosticMessage("1-3-2-1"));
        body132.add(new DiagnosticMessage("1-3-2-2"));
        DiagnosticMessage footer132 = new DiagnosticMessage("1-3-2");
        TraceLog traceLog132 = TraceLog.builder().headerMessage(header132).bodyMessages(body132).footerMessage(footer132).build();

        DiagnosticMessage header13 = new DiagnosticMessage("1-3");
        List<TraceLog> subLog13 = new ArrayList<>();
        subLog13.add(traceLog131);
        subLog13.add(traceLog132);
        DiagnosticMessage footer13 = new DiagnosticMessage("1-3");
        TraceLog traceLog13 = TraceLog.builder().headerMessage(header13).subTraceLogs(subLog13).footerMessage(footer13).build();

        DiagnosticMessage header1 = new DiagnosticMessage("1");
        List<TraceLog> subLog1 = new ArrayList<>();
        subLog1.add(traceLog11);
        subLog1.add(traceLog12);
        subLog1.add(traceLog13);
        DiagnosticMessage footer1 = new DiagnosticMessage("1");
        TraceLog traceLog1 = TraceLog.builder().headerMessage(header1).subTraceLogs(subLog1).footerMessage(footer1).build();

        this.traceLog = traceLog1;

        this.traceLogJsonConverter = new TraceLogJsonConverter();
    }

    @Test
    void when_convert_traceLog_to_json_then_return_json() throws Exception {
        traceLog.traverse(this.traceLogJsonConverter);
        String json = traceLogJsonConverter.getJson();

        ObjectMapper objectMapper = JsonMapper.builder().enable(JsonReadFeature.ALLOW_TRAILING_COMMA).build();
        Map<String, Object> objectMap = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {});

        assertThat(objectMap.get("header")).isEqualTo("1");
        assertThat(objectMap.get("subNodes")).isInstanceOf(List.class);

        List<Object> subNodes = (List<Object>) objectMap.get("subNodes");
        assertThat(subNodes).hasSize(3);
        assertThat(subNodes.get(1)).isInstanceOf(Map.class);

        Map<String, Object> subNode1 = (Map<String, Object>) subNodes.get(1);
        assertThat(subNode1.get("header")).isEqualTo("1-2");
        assertThat(subNode1.get("body")).isInstanceOf(List.class);
        assertThat(subNode1.get("footer")).isEqualTo("1-2");

        List<Object> body = (List<Object>) subNode1.get("body");
        assertThat(body).hasSize(4);
    }
}
