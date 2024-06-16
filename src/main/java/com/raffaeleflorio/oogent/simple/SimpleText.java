package com.raffaeleflorio.oogent.simple;

import com.raffaeleflorio.oogent.Text;

public final class SimpleText implements Text {

    private final String text;

    public SimpleText(final String text) {
        this.text = text;
    }

    @Override
    public String text() {
        return this.text;
    }

    @Override
    public Text then(final Text text) {
        return new SimpleText(this.text.concat(text.text()));
    }
}
