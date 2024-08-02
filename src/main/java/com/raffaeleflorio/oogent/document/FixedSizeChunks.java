package com.raffaeleflorio.oogent.document;

import com.raffaeleflorio.oogent.Document;
import com.raffaeleflorio.oogent.Text;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.stream.IntStream;

public final class FixedSizeChunks implements Document.Chunks {

    private final Text text;
    private final Integer size;

    public FixedSizeChunks(final Text text, final Integer size) {
        this.text = text;
        this.size = size;
    }

    @NotNull
    @Override
    public Iterator<Text> iterator() {
        var textSize = this.text.size();
        return IntStream.range(0, this.chunksCount(textSize))
                .mapToObj(i -> this.text.sub(
                                i * this.size,
                                Math.min((i + 1) * this.size, textSize)
                        )
                )
                .iterator();
    }

    private Integer chunksCount(final Integer textSize) {
        return Math.ceilDiv(textSize, this.size);
    }
}
