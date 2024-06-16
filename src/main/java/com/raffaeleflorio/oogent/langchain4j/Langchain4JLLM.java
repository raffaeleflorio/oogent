package com.raffaeleflorio.oogent.langchain4j;

import com.raffaeleflorio.oogent.LLM;
import dev.langchain4j.model.chat.ChatLanguageModel;

public final class Langchain4JLLM implements LLM {

    private final ChatLanguageModel chatLanguageModel;

    public Langchain4JLLM(final ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
    }

    @Override
    public String completion(final String string) {
        return this.chatLanguageModel.generate(string);
    }
}
