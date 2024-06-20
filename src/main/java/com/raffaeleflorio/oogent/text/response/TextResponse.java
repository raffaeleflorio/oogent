package com.raffaeleflorio.oogent.text.response;

import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.text.PlainText;

public final class TextResponse implements Response {

    private final Text text;

    public TextResponse(final String text) {
        this(new PlainText(text));
    }

    public TextResponse(final Text text) {
        this.text = text;
    }

    @Override
    public String asString() {
        return this.text.asString();
    }

    @Override
    public Response then(final Text text) {
        return new TextResponse(this.text.then(text));
    }

    @Override
    public Boolean contains(final Text text) {
        return this.text.contains(text);
    }

    @Override
    public Response afterFirst(final Text text) {
        return new TextResponse(this.text.afterFirst(text));
    }

    @Override
    public Response afterLast(final Text text) {
        return new TextResponse(this.text.afterLast(text));
    }

    @Override
    public Response beforeFirst(final Text text) {
        return new TextResponse(this.text.beforeFirst(text));
    }

    @Override
    public Response beforeLast(final Text text) {
        return new TextResponse(this.text.beforeLast(text));
    }

    @Override
    public Boolean empty() {
        return this.text.empty();
    }
}
