package com.raffaeleflorio.oogent.document;

import com.raffaeleflorio.oogent.Document;
import com.raffaeleflorio.oogent.Text;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class SeparatedChunks implements Document.Chunks {

    private final Text text;
    private final Text separator;

    public SeparatedChunks(final Text text, final Text separator) {
        this.text = text;
        this.separator = separator;
    }

    @Override
    public Iterator<Text> iterator() {
        return new Iterator<>() {

            private final Text separator = SeparatedChunks.this.separator;
            private Text text = SeparatedChunks.this.text;
            private boolean hasNext = true;

            @Override
            public boolean hasNext() {
                return this.hasNext;
            }

            @Override
            public Text next() {
                if (this.hasNext) {
                    var next = this.text.contains(this.separator) ? this.text.beforeFirst(this.separator) : this.text;
                    this.text = this.text.contains(this.separator) ? this.text.afterFirst(this.separator) : null;
                    this.hasNext = this.text != null && this.text.size() > 0;
                    return next;
                }
                throw new NoSuchElementException();
            }
        };
    }
}
