package com.raffaeleflorio.oogent.agent;

import com.raffaeleflorio.oogent.TokenUsage;

public final class ZeroTokenUsage implements TokenUsage {

    @Override
    public Integer input() {
        return 0;
    }

    @Override
    public Integer output() {
        return 0;
    }

    @Override
    public TokenUsage sum(final TokenUsage other) {
        return other;
    }
}
