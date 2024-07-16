package com.raffaeleflorio.oogent.document;

import com.raffaeleflorio.oogent.Document;
import com.raffaeleflorio.oogent.Text;

final class MissingDocument implements Document {

    private final RuntimeException exception;

    MissingDocument(final Text id) {
        this(
                new IllegalStateException("Missing document ".concat(id.asString()))
        );
    }

    MissingDocument(final RuntimeException exception) {
        this.exception = exception;
    }

    @Override
    public Text id() {
        throw this.exception;
    }

    @Override
    public Text text() {
        throw this.exception;
    }

    @Override
    public Double score() {
        throw this.exception;
    }
}
