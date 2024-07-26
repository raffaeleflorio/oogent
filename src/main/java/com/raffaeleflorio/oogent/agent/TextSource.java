package com.raffaeleflorio.oogent.agent;

import com.raffaeleflorio.oogent.Source;
import com.raffaeleflorio.oogent.Text;

public final class TextSource implements Source {

    private final Text id;
    private final Text text;

    public TextSource(final Text id, final Text text) {
        this.id = id;
        this.text = text;
    }

    @Override
    public Text id() {
        return this.id;
    }

    @Override
    public Text text() {
        return this.text;
    }
}
