package com.raffaeleflorio.oogent.text;

import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.Text;

public final class Trimmed implements Text {

    private final Text origin;

    public Trimmed(final Text origin) {
        this.origin = origin;
    }

    @Override
    public Text then(final Text text) {
        return this.trimmed().then(text);
    }

    private Text trimmed() {
        return new PlainText(this.asString());
    }

    @Override
    public String asString() {
        return this.origin.asString().trim();
    }

    @Override
    public Boolean contains(final Text text) {
        return this.trimmed().contains(text);
    }

    @Override
    public Text afterFirst(final Text text) {
        return this.trimmed().afterFirst(text);
    }

    @Override
    public Text afterLast(final Text text) {
        return this.trimmed().afterLast(text);
    }

    @Override
    public Text beforeFirst(final Text text) {
        return this.trimmed().beforeFirst(text);
    }

    @Override
    public Text beforeLast(final Text text) {
        return this.trimmed().beforeLast(text);
    }

    @Override
    public Boolean startsWith(final Text prefix) {
        return this.trimmed().startsWith(prefix);
    }

    @Override
    public Boolean blank() {
        return this.trimmed().blank();
    }

    @Override
    public Integer size() {
        return this.trimmed().size();
    }

    @Override
    public Text sub(final Integer start, final Integer endExcluded) {
        return this.trimmed().sub(start, endExcluded);
    }
}
