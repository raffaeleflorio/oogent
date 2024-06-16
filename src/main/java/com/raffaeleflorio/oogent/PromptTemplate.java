package com.raffaeleflorio.oogent;

import java.util.Map;

public interface PromptTemplate {

    String prompt(Map<String, String> variables);

    default String prompt() {
        return this.prompt(Map.of());
    }

    default String prompt(String text) {
        return this.prompt(Map.of("it", text));
    }
}
