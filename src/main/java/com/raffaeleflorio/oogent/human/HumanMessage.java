package com.raffaeleflorio.oogent.human;

import com.raffaeleflorio.oogent.Message;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.simple.SimpleText;

public final class HumanMessage implements Message {

    private final Text message;

    public HumanMessage(final String message) {
        this(new SimpleText(message));
    }

    public HumanMessage(final Text message) {
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
    public String asString() {
        return this.message.asString();
    }

    @Override
    public Text then(final Text text) {
        return new HumanMessage(this.message.then(text));
    }

    @Override
    public Boolean contains(final Text text) {
        return this.message.contains(text);
    }

    @Override
    public Text afterLast(final Text text) {
        return this.message.afterLast(text);
    }

    @Override
    public Text beforeFirst(final Text text) {
        return this.message.beforeFirst(text);
    }
}
