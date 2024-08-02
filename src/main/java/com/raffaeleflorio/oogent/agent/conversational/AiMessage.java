package com.raffaeleflorio.oogent.agent.conversational;

import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.Text;

public final class AiMessage implements Message {

    private final Text message;

    public AiMessage(final String message) {
        this(new PlainText(message));
    }

    public AiMessage(final Text message) {
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
    public Message then(final Text text) {
        return new AiMessage(this.message.then(text));
    }

    @Override
    public Boolean contains(final Text text) {
        return this.message.contains(text);
    }

    @Override
    public Message afterFirst(final Text text) {
        return new AiMessage(this.message.afterFirst(text));
    }

    @Override
    public Message afterLast(final Text text) {
        return new AiMessage(this.message.afterLast(text));
    }

    @Override
    public Message beforeFirst(final Text text) {
        return new AiMessage(this.message.beforeFirst(text));
    }

    @Override
    public Message beforeLast(final Text text) {
        return new AiMessage(this.message.beforeLast(text));
    }

    @Override
    public Boolean startsWith(final Text prefix) {
        return this.message.startsWith(prefix);
    }

    @Override
    public Boolean blank() {
        return this.message.blank();
    }

    @Override
    public Integer size() {
        return this.message.size();
    }

    @Override
    public Text sub(final Integer start, final Integer endExcluded) {
        return this.message.sub(start, endExcluded);
    }

    @Override
    public String asString() {
        return this.message.asString();
    }
}
