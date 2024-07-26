package com.raffaeleflorio.oogent.storage;

import com.raffaeleflorio.oogent.Embedding;

import java.util.function.BiFunction;

public final class CosineSimilarityScore implements BiFunction<Embedding, Embedding, Double> {

    @Override
    public Double apply(final Embedding x, final Embedding y) {
        return (new CosineSimilarity().apply(x, y) + 1) / 2;
    }
}
