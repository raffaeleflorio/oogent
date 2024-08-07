package com.raffaeleflorio.oogent.embedding.normalization;

import com.raffaeleflorio.oogent.Embedding;
import com.raffaeleflorio.oogent.embedding.EuclideanNorm;

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
        var norm = new EuclideanNorm().apply(this.origin);
        return this.origin.values().stream().map(x -> x / norm).toList();
    }
}
