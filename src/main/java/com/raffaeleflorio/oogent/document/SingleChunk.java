package com.raffaeleflorio.oogent.document;

import com.raffaeleflorio.oogent.Document;
import com.raffaeleflorio.oogent.Text;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class SingleChunk implements Document.Chunks {

    private final Text chunk;

    public SingleChunk(final Text chunk) {
        this.chunk = chunk;
    }

    @NotNull
    @Override
    public Iterator<Text> iterator() {
        return new Iterator<>() {
            private boolean hasNext = true;

            @Override
            public boolean hasNext() {
                return this.hasNext;
            }

            @Override
            public Text next() {
                if (this.hasNext) {
                    this.hasNext = false;
                    return SingleChunk.this.chunk;
                }
                throw new NoSuchElementException();
            }
        };
    }
}
