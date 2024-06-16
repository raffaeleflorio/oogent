package com.raffaeleflorio.oogent.ai;

import com.raffaeleflorio.oogent.Message;

public final class AiMessage implements Message {

    private final String message;

    public AiMessage(final String message) {
        this.message = message;
    }

    @Override
    public Boolean ai() {
        return true;
    }

    @Override
    public Boolean human() {
        return false;
    }

    @Override
    public String text() {
        return this.message;
    }
}
