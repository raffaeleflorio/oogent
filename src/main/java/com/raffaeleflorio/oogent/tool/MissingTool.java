package com.raffaeleflorio.oogent.tool;

import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.Tool;

final class MissingTool implements Tool {

    private final RuntimeException exception;

    MissingTool(final Text id) {
        this(
                new IllegalStateException("Missing tool ".concat(id.asString()))
        );
    }

    MissingTool(final RuntimeException exception) {
        this.exception = exception;
    }

    @Override
    public Signature signature() {
        throw this.exception;
    }

    @Override
    public Text result(final Text args) {
        throw this.exception;
    }
}
