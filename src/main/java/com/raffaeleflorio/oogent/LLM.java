package com.raffaeleflorio.oogent;

public interface LLM {

    Completion completion(Text text);

    interface Completion {

        Text text();

        TokenUsage tokenUsage();
    }
}
