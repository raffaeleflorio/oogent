package com.raffaeleflorio.oogent;

import java.util.List;
import java.util.stream.Collectors;

public final class Joined implements Text {

    private final List<? extends Text> texts;
    private final Text delimiter;

    public Joined(final List<? extends Text> texts, final Text delimiter) {
        this.texts = texts;
        this.delimiter = delimiter;
    }

    @Override
    public String asString() {
        return this.texts
                .stream()
                .map(Text::asString)
                .collect(Collectors.joining(this.delimiter.asString()));
    }

    @Override
    public Text then(final Text text) {
        return new PlainText(this.asString()).then(text);
    }

    @Override
    public Boolean contains(final Text text) {
        return new PlainText(this.asString()).contains(text);
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
        return this.texts.stream().allMatch(Text::empty);
    }
}
