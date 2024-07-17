package com.raffaeleflorio.oogent.storage.embedding;

import com.raffaeleflorio.oogent.storage.Embedding;

import java.util.function.Function;

public final class EuclideanNorm implements Function<Embedding, Double> {

    @Override
    public Double apply(final Embedding embedding) {
        return Math.sqrt(
                embedding
                        .values()
                        .stream()
                        .reduce(
                                0.0,
                                (left, right) -> left + (right * right)
                        )
        );
    }
}
