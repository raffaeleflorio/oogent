package com.raffaeleflorio.oogent.agent;

import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Sources;
import com.raffaeleflorio.oogent.Text;

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
    public Text then(final Text text) {
        return this.text.then(text);
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

    @Override
    public Boolean startsWith(final Text prefix) {
        return this.text.startsWith(prefix);
    }

    @Override
    public Boolean empty() {
        return this.text.empty();
    }
}
