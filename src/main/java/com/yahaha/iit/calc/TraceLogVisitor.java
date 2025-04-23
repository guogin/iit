package com.yahaha.iit.calc;

import java.util.List;

public interface TraceLogVisitor {
    void start();
    void visitHeader(DiagnosticMessage headerMessage);
    void visitBody(List<DiagnosticMessage> bodyMessages);
    void visitSubNodes(List<TraceLog> subTraceLogs);
    void visitFooter(DiagnosticMessage footerMessage);
    void end();
}
