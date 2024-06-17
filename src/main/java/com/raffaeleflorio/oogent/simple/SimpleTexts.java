package com.raffaeleflorio.oogent.simple;

import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.Texts;
import com.raffaeleflorio.oogent.join.Joined;
import com.raffaeleflorio.oogent.join.Listed;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public final class SimpleTexts implements Texts {

    private final List<Text> texts;

    public SimpleTexts(final List<Text> texts) {
        this.texts = texts;
    }

    @Override
    public Texts then(final Text text) {
        return new SimpleTexts(
                Stream.concat(this.texts.stream(), Stream.of(text)).toList()
        );
    }

    @Override
    public Text joined(final Text delimiter) {
        return new Joined(this.texts, delimiter);
    }

    @Override
    public Text listed() {
        return new Listed(this.texts);
    }

    @NotNull
    @Override
    public Iterator<Text> iterator() {
        return this.texts.iterator();
    }
}
