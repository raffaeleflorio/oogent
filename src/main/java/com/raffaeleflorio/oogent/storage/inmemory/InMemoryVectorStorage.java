package com.raffaeleflorio.oogent.storage.inmemory;

import com.raffaeleflorio.oogent.Document;
import com.raffaeleflorio.oogent.Documents;
import com.raffaeleflorio.oogent.Embedding;
import com.raffaeleflorio.oogent.EmbeddingModel;
import com.raffaeleflorio.oogent.Storage;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.storage.CosineSimilarityScore;
import com.raffaeleflorio.oogent.storage.TextDocument;
import com.raffaeleflorio.oogent.storage.TextDocuments;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public final class InMemoryVectorStorage implements Storage {

    private final EmbeddingModel embeddingModel;
    private final BiFunction<? super Embedding, ? super Embedding, Double> scoreFn;
    private final Map<String, StoredText> map;

    public InMemoryVectorStorage(final EmbeddingModel embeddingModel) {
        this(
                embeddingModel,
                new CosineSimilarityScore()
        );
    }

    public InMemoryVectorStorage(
            final EmbeddingModel embeddingModel,
            final BiFunction<? super Embedding, ? super Embedding, Double> scoreFn
    ) {
        this(
                embeddingModel,
                scoreFn,
                new ConcurrentHashMap<>()
        );
    }

    InMemoryVectorStorage(
            final EmbeddingModel embeddingModel,
            final BiFunction<? super Embedding, ? super Embedding, Double> scoreFn,
            final Map<String, StoredText> map
    ) {
        this.embeddingModel = embeddingModel;
        this.scoreFn = scoreFn;
        this.map = map;
    }

    @Override
    public Documents documents(final Text text, final Integer limit, final Double minScore) {
        var x = this.embeddingModel.embedding(text);
        return new TextDocuments(
                this.map
                        .entrySet()
                        .stream()
                        .<Document>map(entry -> new TextDocument(
                                        entry.getKey(),
                                        entry.getValue().text(),
                                        this.scoreFn.apply(entry.getValue().embedding(), x)
                                )
                        )
                        .filter(doc -> doc.score() >= minScore)
                        .sorted(Comparator.comparing(Document::score))
                        .limit(limit)
        );
    }

    @Override
    public void store(final Text id, final Text text) {
        this.map.put(
                id.asString(),
                new StoredText(
                        text.asString(),
                        this.embeddingModel.embedding(text)
                )
        );
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public void remove(final Text id) {
        this.map.remove(id.asString());
    }

    private record StoredText(String text, Embedding embedding) {
    }
}
