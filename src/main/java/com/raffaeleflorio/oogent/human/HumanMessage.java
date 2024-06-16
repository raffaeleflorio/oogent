package com.raffaeleflorio.oogent.human;

import com.raffaeleflorio.oogent.Message;

public final class HumanMessage implements Message {

    private final String message;

    public HumanMessage(final String message) {
        this.message = message;
    }

    @Override
    public Boolean ai() {
        return false;
    }

    @Override
    public Boolean human() {
        return true;
    }

    @Override
    public String text() {
        return this.message;
    }
}
