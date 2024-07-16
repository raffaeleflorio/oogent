package com.raffaeleflorio.oogent.document;

import com.raffaeleflorio.oogent.Document;
import com.raffaeleflorio.oogent.Text;

public final class TextDocument implements Document {

    private final Text id;
    private final Text text;
    private final Double score;

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
