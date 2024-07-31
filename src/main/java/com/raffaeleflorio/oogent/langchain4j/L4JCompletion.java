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
    public TokenUsage tokenUsage() {
        return new TokenUsage() {
            @Override
            public Integer input() {
                return L4JCompletion.this.response.tokenUsage().inputTokenCount();
            }

            @Override
            public Integer output() {
                return L4JCompletion.this.response.tokenUsage().outputTokenCount();
            }

            @Override
            public Integer total() {
                return L4JCompletion.this.response.tokenUsage().totalTokenCount();
            }
        };
    }

    @Override
    public Text then(final Text text) {
        return this.asText().then(text);
    }

    private Text asText() {
        return new PlainText(this.response.content().text());
    }

    @Override
    public Boolean contains(final Text text) {
        return this.asText().contains(text);
    }

    @Override
    public Text afterFirst(final Text text) {
        return this.asText().afterFirst(text);
    }

    @Override
    public Text afterLast(final Text text) {
        return this.asText().afterLast(text);
    }

    @Override
    public Text beforeFirst(final Text text) {
        return this.asText().beforeFirst(text);
    }

    @Override
    public Text beforeLast(final Text text) {
        return this.asText().beforeLast(text);
    }

    @Override
    public Boolean startsWith(final Text prefix) {
        return this.asText().startsWith(prefix);
    }

    @Override
    public String asString() {
        return this.asText().asString();
    }

    @Override
    public Boolean empty() {
        return this.asText().empty();
    }
}
