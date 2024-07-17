package com.raffaeleflorio.oogent.storage.embedding;

import com.raffaeleflorio.oogent.storage.Embedding;

import java.util.function.BiFunction;

public final class CosineSimilarity implements BiFunction<Embedding, Embedding, Double> {

    @Override
    public Double apply(final Embedding x, final Embedding y) {
        // TODO: two loops could be avoided...
        return new DotProduct().apply(x, y) / (new EuclideanNorm().apply(x) * new EuclideanNorm().apply(y));
    }
}
