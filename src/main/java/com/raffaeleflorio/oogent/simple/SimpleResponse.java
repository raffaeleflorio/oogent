package com.raffaeleflorio.oogent.simple;

import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;

public final class SimpleResponse implements Response {

    private final Text text;

    public SimpleResponse(final String text) {
        this(new SimpleText(text));
    }

    public SimpleResponse(final Text text) {
        this.text = text;
    }

    @Override
    public String asString() {
        return this.text.asString();
    }

    @Override
    public Text then(final Text text) {
        return new SimpleResponse(this.text.then(text));
    }

    @Override
    public Boolean contains(final Text text) {
        return this.text.contains(text);
    }

    @Override
    public Text afterLast(final Text text) {
        return this.text.afterLast(text);
    }

    @Override
    public Text beforeFirst(final Text text) {
        return this.text.beforeFirst(text);
    }
}
