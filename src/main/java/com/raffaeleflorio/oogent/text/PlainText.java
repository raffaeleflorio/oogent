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
    public Text afterLast(final Text text) {
        var i = this.text.lastIndexOf(text.asString());
        return new PlainText(this.text.substring(i + text.asString().length()));
    }

    @Override
    public Text beforeFirst(final Text text) {
        var i = this.text.indexOf(text.asString());
        return new PlainText(this.text.substring(0, i));
    }
}
