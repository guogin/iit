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

    @BeforeEach
    void setUp() {
        DiagnosticMessage header1 = new DiagnosticMessage("1");
        List<TraceLog> subLog1 = new ArrayList<>();
        subLog1.add(DummyTraceLogProvider.createTraceLog11());
        subLog1.add(DummyTraceLogProvider.createTraceLog12());
        subLog1.add(DummyTraceLogProvider.createTraceLog13());
        DiagnosticMessage footer1 = new DiagnosticMessage("1");
        TraceLog traceLog1 = TraceLog.builder().headerMessage(header1).subTraceLogs(subLog1).footerMessage(footer1).build();

        this.traceLog = traceLog1;
    }

    @Test
    void when_convert_traceLog_to_json_then_return_json() throws Exception {
        String json = TraceLogJsonConverter.convert(this.traceLog);

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
