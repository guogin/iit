package com.yahaha.iit.calc;

import java.util.List;

public class TraceLogJsonConverter implements TraceLogVisitor {
    private StringBuffer stringBuffer = new StringBuffer();

    @Override
    public void start() {
        stringBuffer.append("{");
    }

    @Override
    public void visitHeader(DiagnosticMessage headerMessage) {
        stringBuffer.append("\"header\": \"").append(headerMessage.getMessage()).append("\",");
    }

    @Override
    public void visitBody(List<DiagnosticMessage> bodyMessages) {
        stringBuffer.append("\"body\": [");
        for (DiagnosticMessage message : bodyMessages) {
            stringBuffer.append("\"").append(message.getMessage()).append("\",");
        }
        stringBuffer.append("],");
    }

    @Override
    public void visitSubNodes(List<TraceLog> subTraceLogs) {
        stringBuffer.append("\"subNodes\": [");
        for (TraceLog traceLog : subTraceLogs) {
            TraceLogJsonConverter subVisitor = new TraceLogJsonConverter();
            traceLog.traverse(subVisitor);
            stringBuffer.append(subVisitor.getJson()).append(",");
        }
        stringBuffer.append("],");
    }

    @Override
    public void visitFooter(DiagnosticMessage footerMessage) {
        stringBuffer.append("\"footer\": \"").append(footerMessage.getMessage()).append("\"");
    }

    @Override
    public void end() {
        stringBuffer.append("}");
    }

    public String getJson() {
        return stringBuffer.toString();
    }
}
