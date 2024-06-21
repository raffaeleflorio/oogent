package com.raffaeleflorio.oogent.text.response;

import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Sources;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.text.PlainText;
import com.raffaeleflorio.oogent.text.source.EmptySources;

public final class TextResponse implements Response {

    private final Text text;
    private final Sources sources;

    public TextResponse(final String text) {
        this(new PlainText(text));
    }

    public TextResponse(final Text text) {
        this(text, new EmptySources());
    }

    public TextResponse(final Text text, final Sources sources) {
        this.text = text;
        this.sources = sources;
    }

    @Override
    public Sources sources() {
        return this.sources;
    }

    @Override
    public String asString() {
        return this.text.asString();
    }

    @Override
    public Response then(final Text text) {
        return new TextResponse(this.text.then(text), this.sources);
    }

    @Override
    public Boolean contains(final Text text) {
        return this.text.contains(text);
    }

    @Override
    public Response afterFirst(final Text text) {
        return new TextResponse(this.text.afterFirst(text), this.sources);
    }

    @Override
    public Response afterLast(final Text text) {
        return new TextResponse(this.text.afterLast(text), this.sources);
    }

    @Override
    public Response beforeFirst(final Text text) {
        return new TextResponse(this.text.beforeFirst(text), this.sources);
    }

    @Override
    public Response beforeLast(final Text text) {
        return new TextResponse(this.text.beforeLast(text), this.sources);
    }

    @Override
    public Boolean startsWith(final Text prefix) {
        return this.text.startsWith(prefix);
    }

    @Override
    public Boolean empty() {
        return this.text.empty();
    }
}
