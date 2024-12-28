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
    public Text then(final Text text) {
        return this.message.then(text);
    }

    @Override
    public Boolean contains(final Text subText) {
        return this.message.contains(subText);
    }

    @Override
    public Text afterFirst(final Text subText) {
        return this.message.afterFirst(subText);
    }

    @Override
    public Text afterLast(final Text subText) {
        return this.message.afterLast(subText);
    }

    @Override
    public Text beforeFirst(final Text subText) {
        return this.message.beforeFirst(subText);
    }

    @Override
    public Text beforeLast(final Text subText) {
        return this.message.beforeLast(subText);
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
