package com.raffaeleflorio.oogent.agent;

import com.raffaeleflorio.oogent.TokenUsage;

public final class ConstTokenUsage implements TokenUsage {

    private final Integer input;
    private final Integer output;

    public ConstTokenUsage(final Integer input, final Integer output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public Integer input() {
        return this.input;
    }

    @Override
    public Integer output() {
        return this.output;
    }

    @Override
    public TokenUsage sum(final TokenUsage other) {
        return new ConstTokenUsage(
                this.input() + other.input(),
                this.output() + other.output()
        );
    }
}
