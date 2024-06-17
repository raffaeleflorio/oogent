package com.raffaeleflorio.oogent.join;

import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.simple.SimpleText;

import java.util.List;
import java.util.stream.Collectors;

public final class Joined implements Text {

    private final List<Text> texts;
    private final Text delimiter;

    public Joined(final List<Text> texts, final Text delimiter) {
        this.texts = texts;
        this.delimiter = delimiter;
    }

    @Override
    public String asString() {
        return this.texts
                .stream()
                .map(Text::asString)
                .collect(Collectors.joining(this.delimiter.asString()));
    }

    @Override
    public Text then(final Text text) {
        return new SimpleText(this.asString()).then(text);
    }
}
