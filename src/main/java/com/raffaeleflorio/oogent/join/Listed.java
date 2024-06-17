package com.raffaeleflorio.oogent.join;

import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.simple.SimpleText;

import java.util.List;

public final class Listed implements Text {

    private final Text origin;

    public Listed(final List<Text> texts) {
        this(new Joined(texts, new SimpleText("\n")));
    }

    Listed(final Text origin) {
        this.origin = origin;
    }

    @Override
    public String asString() {
        return this.origin.asString();
    }

    @Override
    public Text then(final Text text) {
        return this.origin.then(text);
    }
}
