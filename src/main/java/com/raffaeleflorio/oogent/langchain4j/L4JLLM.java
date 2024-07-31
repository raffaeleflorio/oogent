package com.raffaeleflorio.oogent.langchain4j;

import com.raffaeleflorio.oogent.LLM;
import com.raffaeleflorio.oogent.Text;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;

public final class L4JLLM implements LLM {

    private final ChatLanguageModel chatLanguageModel;

    public L4JLLM(final ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
    }

    @Override
    public Completion completion(final Text text) {
        return new L4JCompletion(
                this.chatLanguageModel.generate(UserMessage.from(text.asString()))
        );
    }
}
