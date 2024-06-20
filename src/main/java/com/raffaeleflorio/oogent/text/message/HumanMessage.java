package com.raffaeleflorio.oogent.text.message;

import com.raffaeleflorio.oogent.Message;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.text.PlainText;

public final class HumanMessage implements Message {

    private final Text message;

    public HumanMessage(final String message) {
        this(new PlainText(message));
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
    public Message then(final Text text) {
        return new HumanMessage(this.message.then(text));
    }

    @Override
    public Boolean contains(final Text text) {
        return this.message.contains(text);
    }

    @Override
    public Message afterFirst(final Text text) {
        return new HumanMessage(this.message.afterFirst(text));
    }

    @Override
    public Message afterLast(final Text text) {
        return new HumanMessage(this.message.afterLast(text));
    }

    @Override
    public Message beforeFirst(final Text text) {
        return new HumanMessage(this.message.beforeFirst(text));
    }

    @Override
    public Message beforeLast(final Text text) {
        return new HumanMessage(this.message.beforeLast(text));
    }

    @Override
    public Boolean empty() {
        return this.message.empty();
    }
}
