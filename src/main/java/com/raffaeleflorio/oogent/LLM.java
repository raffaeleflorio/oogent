package com.raffaeleflorio.oogent;

public interface LLM {

    Completion completion(Text text);

    interface Completion extends Text {

        TokenUsage tokenUsage();
    }
}
