package com.raffaeleflorio.oogent.text;

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
    public String asString() {
        return this.origin.asString().toLowerCase(this.locale);
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
        return this.origin.empty();
    }
}
