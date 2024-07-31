package com.raffaeleflorio.oogent;

public interface TokenUsage {

    Integer input();

    Integer output();

    TokenUsage sum(TokenUsage other);

    default Integer total() {
        return this.input() + this.output();
    }
}
