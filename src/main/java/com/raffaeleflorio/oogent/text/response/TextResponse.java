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
    public Text then(final Text text) {
        return new TextResponse(this.text.then(text));
    }

    @Override
    public Boolean contains(final Text text) {
        return this.text.contains(text);
    }

    @Override
    public Text afterFirst(final Text text) {
        return this.text.afterFirst(text);
    }

    @Override
    public Text afterLast(final Text text) {
        return this.text.afterLast(text);
    }

    @Override
    public Text beforeFirst(final Text text) {
        return this.text.beforeFirst(text);
    }

    @Override
    public Text beforeLast(final Text text) {
        return this.text.beforeLast(text);
    }
}
