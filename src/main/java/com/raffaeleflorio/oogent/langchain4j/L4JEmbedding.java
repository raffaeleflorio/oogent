package com.raffaeleflorio.oogent.langchain4j;

import com.raffaeleflorio.oogent.Embedding;

import java.util.List;

final class L4JEmbedding implements Embedding {

    private final dev.langchain4j.data.embedding.Embedding embedding;

    L4JEmbedding(final dev.langchain4j.data.embedding.Embedding embedding) {
        this.embedding = embedding;
    }

    @Override
    public Integer dimension() {
        return this.embedding.dimension();
    }

    @Override
    public List<Double> asList() {
        return this.embedding.vectorAsList().stream().map(Number::doubleValue).toList();
    }
}
