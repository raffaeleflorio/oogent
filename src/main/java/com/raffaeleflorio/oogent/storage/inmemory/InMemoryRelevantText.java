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
    public Boolean contains(final Text subText) {
        return this.text.contains(subText);
    }

    @Override
    public Text afterFirst(final Text subText) {
        return this.text.afterFirst(subText);
    }

    @Override
    public Text afterLast(final Text subText) {
        return this.text.afterLast(subText);
    }

    @Override
    public Text beforeFirst(final Text subText) {
        return this.text.beforeFirst(subText);
    }

    @Override
    public Text beforeLast(final Text subText) {
        return this.text.beforeLast(subText);
    }

    @Override
    public Boolean startsWith(final Text prefix) {
        return this.text.startsWith(prefix);
    }

    @Override
    public Boolean blank() {
        return this.text.blank();
    }

    @Override
    public Integer size() {
        return this.text.size();
    }

    @Override
    public Text sub(final Integer start, final Integer endExcluded) {
        return this.text.sub(start, endExcluded);
    }

    @Override
    public String asString() {
        return this.text.asString();
    }
}
