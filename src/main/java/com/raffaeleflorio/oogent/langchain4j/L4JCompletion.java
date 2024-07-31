package com.raffaeleflorio.oogent.langchain4j;

import com.raffaeleflorio.oogent.LLM;
import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.TokenUsage;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.output.Response;

final class L4JCompletion implements LLM.Completion {

    private final Response<AiMessage> response;

    L4JCompletion(final Response<AiMessage> response) {
        this.response = response;
    }

    @Override
    public Text text() {
        return new PlainText(this.response.content().text());
    }

    @Override
    public TokenUsage tokenUsage() {
        return new L4JTokenUsage(this.response.tokenUsage());
    }
}
