package com.yahaha.iit.calc;

import java.util.List;

public class TraceLogJsonConverter  {
    public static String convert(TraceLog traceLog) {
        TraceLogJsonVisitor visitor = new TraceLogJsonVisitor();
        traceLog.traverse(visitor);
        return visitor.getJson();
    }

    private static class TraceLogJsonVisitor implements TraceLogVisitor {
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
                TraceLogJsonVisitor subLogVisitor = new TraceLogJsonVisitor();
                traceLog.traverse(subLogVisitor);
                stringBuffer.append(subLogVisitor.getJson()).append(",");
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
}
