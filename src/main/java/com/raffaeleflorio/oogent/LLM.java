package com.raffaeleflorio.oogent;

public interface LLM {

    Completion completion(Text prompt);

    interface Completion {

        Text text();

        TokenUsage tokenUsage();
    }
}
