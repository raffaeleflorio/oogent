package com.raffaeleflorio.oogent.text;

import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.Text;

import java.util.Locale;

public final class Lowered implements Text {

    private final Text origin;
    private final Locale locale;

    public Lowered(final Text origin) {
        this(origin, Locale.ENGLISH);
    }

    public Lowered(final Text origin, final Locale locale) {
        this.origin = origin;
        this.locale = locale;
    }

    @Override
    public Text then(final Text text) {
        return this.lowered().then(text);
    }

    private Text lowered() {
        return new PlainText(this.asString());
    }

    @Override
    public String asString() {
        return this.origin.asString().toLowerCase(this.locale);
    }

    @Override
    public Boolean contains(final Text text) {
        return this.lowered().contains(text);
    }

    @Override
    public Text afterFirst(final Text text) {
        return this.lowered().afterFirst(text);
    }

    @Override
    public Text afterLast(final Text text) {
        return this.lowered().afterLast(text);
    }

    @Override
    public Text beforeFirst(final Text text) {
        return this.lowered().beforeFirst(text);
    }

    @Override
    public Text beforeLast(final Text text) {
        return this.lowered().beforeLast(text);
    }

    @Override
    public Boolean startsWith(final Text prefix) {
        return this.lowered().startsWith(prefix);
    }

    @Override
    public Boolean blank() {
        return this.lowered().blank();
    }

    @Override
    public Integer size() {
        return this.lowered().size();
    }

    @Override
    public Text sub(final Integer start, final Integer endExcluded) {
        return this.lowered().sub(start, endExcluded);
    }
}
