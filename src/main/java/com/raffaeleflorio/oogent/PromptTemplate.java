package com.raffaeleflorio.oogent;

import java.util.Map;

public interface PromptTemplate {

    Text prompt(Map<Text, Text> variables);

    Text prompt(Text text);

    default Text prompt() {
        return this.prompt(Map.of());
    }
}
