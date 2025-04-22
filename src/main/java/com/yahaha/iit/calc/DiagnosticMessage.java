package com.yahaha.iit.calc;

import lombok.*;

import java.text.MessageFormat;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class DiagnosticMessage {
    private String pattern;
    private Object[] arguments;

    public DiagnosticMessage(String pattern, Object... arguments) {
        this.pattern = pattern;
        this.arguments = arguments;
    }

    public String getMessage() {
        return MessageFormat.format(this.pattern, this.arguments);
    }
}
