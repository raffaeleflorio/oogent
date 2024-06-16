package com.raffaeleflorio.oogent.ai;

import com.raffaeleflorio.oogent.Message;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.simple.SimpleText;

public final class AiMessage implements Message {

    private final Text message;

    public AiMessage(final String message) {
        this(new SimpleText(message));
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
    public String text() {
        return this.message.text();
    }

    @Override
    public Text then(final Text text) {
        return new AiMessage(this.message.then(text));
    }
}
