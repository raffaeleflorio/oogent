package com.raffaeleflorio.oogent.document;

import com.raffaeleflorio.oogent.Document;
import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.Text;

import java.util.UUID;
import java.util.function.Function;

public final class TextDocument implements Document {

    private final Text text;
    private final Text id;
    private final Function<? super Text, ? extends Chunks> chunksFn;

    public TextDocument(final Text text) {
        this(
                text,
                new PlainText(UUID.randomUUID().toString())
        );
    }

    public TextDocument(final Text text, final Text id) {
        this(
                text,
                id,
                SingleChunk::new
        );
    }

    public TextDocument(final Text text, final Text id, final Function<? super Text, ? extends Chunks> chunksFn) {
        this.text = text;
        this.id = id;
        this.chunksFn = chunksFn;
    }

    @Override
    public Text id() {
        return this.id;
    }

    @Override
    public Text text() {
        return this.text;
    }

    @Override
    public Chunks chunks() {
        return this.chunksFn.apply(this.text);
    }
}
