package com.raffaeleflorio.oogent.simple;

import com.raffaeleflorio.oogent.Response;

public final class SimpleResponse implements Response {

    private final String text;

    public SimpleResponse(final String text) {
        this.text = text;
    }

    @Override
    public String text() {
        return this.text;
    }
}
