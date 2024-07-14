package com.raffaeleflorio.oogent.text.source;

import com.raffaeleflorio.oogent.Source;
import com.raffaeleflorio.oogent.Text;

public final class MissingSource implements Source {

    private final RuntimeException exception;

    public MissingSource(final Text id) {
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
    public String asString() {
        throw this.exception;
    }

    @Override
    public Source then(final Text text) {
        throw this.exception;
    }

    @Override
    public Boolean contains(final Text text) {
        throw this.exception;
    }

    @Override
    public Source afterFirst(final Text text) {
        throw this.exception;
    }

    @Override
    public Source afterLast(final Text text) {
        throw this.exception;
    }

    @Override
    public Source beforeFirst(final Text text) {
        throw this.exception;
    }

    @Override
    public Source beforeLast(final Text text) {
        throw this.exception;
    }

    @Override
    public Boolean startsWith(final Text prefix) {
        throw this.exception;
    }

    @Override
    public Boolean empty() {
        throw this.exception;
    }
}
