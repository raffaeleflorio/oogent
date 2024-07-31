package com.raffaeleflorio.oogent.langchain4j;

import com.raffaeleflorio.oogent.TokenUsage;

final class L4JTokenUsage implements TokenUsage {

    private final dev.langchain4j.model.output.TokenUsage tokenUsage;

    L4JTokenUsage(final dev.langchain4j.model.output.TokenUsage tokenUsage) {
        this.tokenUsage = tokenUsage;
    }

    @Override
    public Integer input() {
        return this.normalised(this.tokenUsage.inputTokenCount());
    }

    private Integer normalised(final Integer value) {
        return value == null ? 0 : value;
    }

    @Override
    public Integer output() {
        return this.normalised(this.tokenUsage.outputTokenCount());
    }

    @Override
    public TokenUsage sum(final TokenUsage other) {
        return new L4JTokenUsage(
                new dev.langchain4j.model.output.TokenUsage(
                        this.input() + other.input(),
                        this.output() + other.output()
                )
        );
    }
}
