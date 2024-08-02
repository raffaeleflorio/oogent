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
    public Text then(final Text text) {
        return this.joined().then(text);
    }

    private Text joined() {
        return new PlainText(this.asString());
    }

    @Override
    public String asString() {
        return this.texts
                .stream()
                .map(Text::asString)
                .collect(Collectors.joining(this.delimiter.asString()));
    }

    @Override
    public Boolean contains(final Text text) {
        return this.joined().contains(text);
    }

    @Override
    public Text afterFirst(final Text text) {
        return this.joined().afterFirst(text);
    }

    @Override
    public Text afterLast(final Text text) {
        return this.joined().afterLast(text);
    }

    @Override
    public Text beforeFirst(final Text text) {
        return this.joined().beforeFirst(text);
    }

    @Override
    public Text beforeLast(final Text text) {
        return this.joined().beforeLast(text);
    }

    @Override
    public Boolean startsWith(final Text prefix) {
        return this.joined().startsWith(prefix);
    }

    @Override
    public Boolean blank() {
        return this.texts.stream().allMatch(Text::blank);
    }

    @Override
    public Integer size() {
        return this.joined().size();
    }

    @Override
    public Text sub(final Integer start, final Integer endExcluded) {
        return this.joined().sub(start, endExcluded);
    }
}
