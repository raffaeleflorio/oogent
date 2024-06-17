package com.raffaeleflorio.oogent.langchain4j;

import com.raffaeleflorio.oogent.LLM;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.simple.SimpleText;
import dev.langchain4j.model.chat.ChatLanguageModel;

public final class Langchain4JLLM implements LLM {

    private final ChatLanguageModel chatLanguageModel;

    public Langchain4JLLM(final ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
    }

    @Override
    public Text completion(final Text text) {
        return new SimpleText(this.chatLanguageModel.generate(text.asString()));
    }
}
