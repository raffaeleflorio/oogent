package com.raffaeleflorio.oogent;

import java.util.List;

public final class Listed implements Text {

    private final Text origin;

    public Listed(final List<? extends Text> texts) {
        this(new Joined(texts, new PlainText("\n")));
    }

    Listed(final Text origin) {
        this.origin = origin;
    }

    @Override
    public String asString() {
        return this.origin.asString();
    }

    @Override
    public Text then(final Text text) {
        return this.origin.then(text);
    }

    @Override
    public Boolean contains(final Text text) {
        return this.origin.contains(text);
    }

    @Override
    public Text afterFirst(final Text text) {
        return this.origin.afterFirst(text);
    }

    @Override
    public Text afterLast(final Text text) {
        return this.origin.afterLast(text);
    }

    @Override
    public Text beforeFirst(final Text text) {
        return this.origin.beforeFirst(text);
    }

    @Override
    public Text beforeLast(final Text text) {
        return this.origin.beforeLast(text);
    }

    @Override
    public Boolean startsWith(final Text prefix) {
        return this.origin.startsWith(prefix);
    }

    @Override
    public Boolean empty() {
        return this.origin.empty();
    }
}
