package com.raffaeleflorio.oogent;

public final class Trimmed implements Text {

    private final Text origin;

    public Trimmed(final Text origin) {
        this.origin = origin;
    }

    @Override
    public String asString() {
        return this.origin.asString().trim();
    }

    @Override
    public Text then(final Text text) {
        return new PlainText(this.asString()).then(text);
    }

    @Override
    public Boolean contains(final Text text) {
        return this.asString().contains(text.asString());
    }

    @Override
    public Text afterFirst(final Text text) {
        return new PlainText(this.asString()).afterFirst(text);
    }

    @Override
    public Text afterLast(final Text text) {
        return new PlainText(this.asString()).afterLast(text);
    }

    @Override
    public Text beforeFirst(final Text text) {
        return new PlainText(this.asString()).beforeFirst(text);
    }

    @Override
    public Text beforeLast(final Text text) {
        return new PlainText(this.asString()).beforeLast(text);
    }

    @Override
    public Boolean startsWith(final Text prefix) {
        return this.asString().startsWith(prefix.asString());
    }

    @Override
    public Boolean empty() {
        return this.asString().isEmpty();
    }
}
