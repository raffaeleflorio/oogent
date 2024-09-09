package com.raffaeleflorio.oogent.document;

import com.raffaeleflorio.oogent.Document;
import com.raffaeleflorio.oogent.Text;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class DelimitedChunks implements Document.Chunks {

    private final Text text;
    private final Text delimiter;

    public DelimitedChunks(final Text text, final Text delimiter) {
        this.text = text;
        this.delimiter = delimiter;
    }

    @Override
    public Iterator<Text> iterator() {
        return new Iterator<>() {

            private final Text delimiter = DelimitedChunks.this.delimiter;
            private Text text = DelimitedChunks.this.text;
            private boolean hasNext = true;

            @Override
            public boolean hasNext() {
                return this.hasNext;
            }

            @Override
            public Text next() {
                if (this.hasNext) {
                    var next = this.text.contains(this.delimiter) ? this.text.beforeFirst(this.delimiter) : this.text;
                    this.text = this.text.contains(this.delimiter) ? this.text.afterFirst(this.delimiter) : null;
                    this.hasNext = this.text != null && this.text.size() > 0;
                    return next;
                }
                throw new NoSuchElementException();
            }
        };
    }
}
