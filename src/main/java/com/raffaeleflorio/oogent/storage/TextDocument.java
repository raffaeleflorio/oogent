package com.raffaeleflorio.oogent.storage;

import com.raffaeleflorio.oogent.Document;
import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.Text;

public final class TextDocument implements Document {

    private final Text id;
    private final Text text;
    private final Double score;

    public TextDocument(final String id, final String text, final Double score) {
        this(new PlainText(id), new PlainText(text), score);
    }

    public TextDocument(final Text id, final Text text, final Double score) {
        this.id = id;
        this.text = text;
        this.score = score;
    }

    @Override
    public Text id() {
        return this.id;
    }

    @Override
    public Text text() {
        return this.text;
    }

    @Override
    public Double score() {
        return this.score;
    }
}
