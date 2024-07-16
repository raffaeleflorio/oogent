package com.raffaeleflorio.oogent.embedding;

import com.raffaeleflorio.oogent.Embedding;

import java.util.List;

public final class EuclideanNormalizedEmbedding implements Embedding {

    private final Embedding origin;

    public EuclideanNormalizedEmbedding(final Embedding origin) {
        this.origin = origin;
    }

    @Override
    public Integer dimension() {
        return this.origin.dimension();
    }

    @Override
    public List<Double> values() {
        var norm = this.norm();
        return this.origin.values().stream().map(x -> x / norm).toList();

    }

    private Double norm() {
        return Math.sqrt(
                this.origin
                        .values()
                        .stream()
                        .reduce(
                                0.0,
                                (left, right) -> left + (right * right)
                        )
        );
    }
}
