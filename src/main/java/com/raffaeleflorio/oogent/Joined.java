package com.raffaeleflorio.oogent;

import java.util.List;
import java.util.stream.Collectors;

public final class Joined implements Text {

    private final Text delimiter;
    private final List<? extends Text> texts;

    public Joined(final Text delimiter, final List<? extends Text> texts) {
        this.delimiter = delimiter;
        this.texts = texts;
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
    public Boolean contains(final Text subText) {
        return this.joined().contains(subText);
    }

    @Override
    public Text afterFirst(final Text subText) {
        return this.joined().afterFirst(subText);
    }

    @Override
    public Text afterLast(final Text subText) {
        return this.joined().afterLast(subText);
    }

    @Override
    public Text beforeFirst(final Text subText) {
        return this.joined().beforeFirst(subText);
    }

    @Override
    public Text beforeLast(final Text subText) {
        return this.joined().beforeLast(subText);
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
