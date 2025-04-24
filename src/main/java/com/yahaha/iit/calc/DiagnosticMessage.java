package com.yahaha.iit.calc;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    @JsonValue
    public String getMessage() {
        return MessageFormat.format(this.pattern, this.arguments);
    }
}
