package com.raffaeleflorio.oogent.agent;

import com.raffaeleflorio.oogent.Source;
import com.raffaeleflorio.oogent.Text;

final class MissingSource implements Source {

    private final RuntimeException exception;

    MissingSource(final Text id) {
        this(
                new IllegalStateException("Missing source ".concat(id.asString()))
        );
    }

    MissingSource(final RuntimeException exception) {
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
}
