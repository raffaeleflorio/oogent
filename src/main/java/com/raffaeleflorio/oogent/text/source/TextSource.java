package com.raffaeleflorio.oogent.text.source;

import com.raffaeleflorio.oogent.Source;
import com.raffaeleflorio.oogent.Text;

public final class TextSource implements Source {

    private final Text id;
    private final Text text;

    public TextSource(final Text id, final Text text) {
        this.id = id;
        this.text = text;
    }

    @Override
    public Text id() {
        return this.id;
    }

    @Override
    public String asString() {
        return this.text.asString();
    }

    @Override
    public Source then(final Text text) {
        return new TextSource(this.id, this.text.then(text));
    }

    @Override
    public Boolean contains(final Text text) {
        return this.text.contains(text);
    }

    @Override
    public Source afterFirst(final Text text) {
        return new TextSource(this.id, this.text.afterFirst(text));
    }

    @Override
    public Source afterLast(final Text text) {
        return new TextSource(this.id, this.text.afterLast(text));
    }

    @Override
    public Source beforeFirst(final Text text) {
        return new TextSource(this.id, this.text.beforeFirst(text));
    }

    @Override
    public Source beforeLast(final Text text) {
        return new TextSource(this.id, this.text.beforeLast(text));
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
