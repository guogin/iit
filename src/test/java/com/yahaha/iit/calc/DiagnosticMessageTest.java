package com.yahaha.iit.calc;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DiagnosticMessageTest {
    @Test
    void when_create_message_without_argument_then_argument_should_not_be_null() {
        DiagnosticMessage message = new DiagnosticMessage("Test");
        assertThat(message.getArguments()).isNotNull();
    }

    @Test
    void when_create_message_with_argument_then_getMessage_should_contain_argument() {
        DiagnosticMessage message = new DiagnosticMessage("Hello, {0}, let's go!", "Yahaha");
        assertThat(message.getMessage()).contains("Yahaha");
    }
}
