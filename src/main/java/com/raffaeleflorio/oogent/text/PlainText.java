package com.raffaeleflorio.oogent.text;

import com.raffaeleflorio.oogent.Text;

public final class PlainText implements Text {

    private final String text;

    public PlainText(final String text) {
        this.text = text;
    }

    @Override
    public String asString() {
        return this.text;
    }

    @Override
    public Text then(final Text text) {
        return new PlainText(this.text.concat(text.asString()));
    }

    @Override
    public Boolean contains(final Text text) {
        return this.text.contains(text.asString());
    }

    @Override
    public Text afterFirst(final Text text) {
        return this.substring(
                this.text.indexOf(text.asString()) + text.asString().length(),
                this.text.length()
        );
    }

    private Text substring(final Integer begin, final Integer end) {
        return new PlainText(this.text.substring(begin, end));
    }

    @Override
    public Text afterLast(final Text text) {
        return this.substring(
                this.text.lastIndexOf(text.asString()) + text.asString().length(),
                this.text.length()
        );
    }

    @Override
    public Text beforeFirst(final Text text) {
        return this.substring(
                0,
                this.text.indexOf(text.asString())
        );
    }

    @Override
    public Text beforeLast(final Text text) {
        return this.substring(
                0,
                this.text.lastIndexOf(text.asString())
        );
    }

    @Override
    public Boolean empty() {
        return this.text.isBlank();
    }
}
