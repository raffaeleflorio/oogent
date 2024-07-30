package com.raffaeleflorio.oogent.storage.inmemory;

import com.raffaeleflorio.oogent.Document;
import com.raffaeleflorio.oogent.RelevantText;
import com.raffaeleflorio.oogent.Source;
import com.raffaeleflorio.oogent.Text;

final class InMemoryRelevantText implements RelevantText {

    private final Text text;
    private final Double score;
    private final Document source;

    InMemoryRelevantText(final Text text, final Double score, final Document source) {
        this.text = text;
        this.score = score;
        this.source = source;
    }

    @Override
    public Double score() {
        return this.score;
    }

    @Override
    public Source source() {
        return new Source() {
            @Override
            public Text id() {
                return InMemoryRelevantText.this.source.id();
            }

            @Override
            public Text text() {
                return InMemoryRelevantText.this.source.text();
            }
        };
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
    public String asString() {
        return this.text.asString();
    }

    @Override
    public Boolean empty() {
        return this.text.empty();
    }
}
