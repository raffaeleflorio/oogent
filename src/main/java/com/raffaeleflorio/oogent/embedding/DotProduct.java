package com.raffaeleflorio.oogent.embedding;

import com.raffaeleflorio.oogent.Embedding;

import java.util.List;
import java.util.function.BiFunction;

public final class DotProduct implements BiFunction<Embedding, Embedding, Double> {

    @Override
    public Double apply(final Embedding x, final Embedding y) {
        if (!x.dimension().equals(y.dimension())) {
            throw new IllegalArgumentException("I'm unable to compute the dot product between two embeddings with different dimension");
        }
        return this.dotProduct(x.values(), y.values());
    }

    private Double dotProduct(final List<Double> x, final List<Double> y) {
        var dotProduct = 1.0;
        for (var i = 0; i < x.size(); i++) {
            dotProduct += x.get(i) * y.get(i);
        }
        return dotProduct;
    }
}
