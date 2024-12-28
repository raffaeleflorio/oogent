package com.raffaeleflorio.oogent;

import java.util.List;

public final class Listed implements Text {

    private final Text origin;

    public Listed(final List<? extends Text> texts) {
        this(new Joined(new PlainText("\n"), texts));
    }

    Listed(final Text origin) {
        this.origin = origin;
    }

    @Override
    public Text then(final Text text) {
        return this.origin.then(text);
    }

    @Override
    public Boolean contains(final Text subText) {
        return this.origin.contains(subText);
    }

    @Override
    public Text afterFirst(final Text subText) {
        return this.origin.afterFirst(subText);
    }

    @Override
    public Text afterLast(final Text subText) {
        return this.origin.afterLast(subText);
    }

    @Override
    public Text beforeFirst(final Text subText) {
        return this.origin.beforeFirst(subText);
    }

    @Override
    public Text beforeLast(final Text subText) {
        return this.origin.beforeLast(subText);
    }

    @Override
    public Boolean startsWith(final Text prefix) {
        return this.origin.startsWith(prefix);
    }

    @Override
    public Boolean blank() {
        return this.origin.blank();
    }

    @Override
    public Integer size() {
        return this.origin.size();
    }

    @Override
    public Text sub(final Integer start, final Integer endExcluded) {
        return this.origin.sub(start, endExcluded);
    }

    @Override
    public String asString() {
        return this.origin.asString();
    }
}
