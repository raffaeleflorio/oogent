package com.raffaeleflorio.oogent.langchain4j;

import com.raffaeleflorio.oogent.Embedding;
import com.raffaeleflorio.oogent.EmbeddingModel;
import com.raffaeleflorio.oogent.Text;

public final class L4JEmbeddingModel implements EmbeddingModel {

    private final dev.langchain4j.model.embedding.EmbeddingModel embeddingModel;

    public L4JEmbeddingModel(final dev.langchain4j.model.embedding.EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    @Override
    public Embedding embedding(final Text text) {
        return new L4JEmbedding(this.embeddingModel.embed(text.asString()).content());
    }
}
