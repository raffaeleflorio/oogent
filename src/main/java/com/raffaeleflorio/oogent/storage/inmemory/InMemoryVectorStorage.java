package com.raffaeleflorio.oogent.storage.inmemory;

import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.storage.Document;
import com.raffaeleflorio.oogent.storage.Documents;
import com.raffaeleflorio.oogent.storage.Embedding;
import com.raffaeleflorio.oogent.storage.EmbeddingModel;
import com.raffaeleflorio.oogent.storage.Storage;
import com.raffaeleflorio.oogent.storage.document.TextDocument;
import com.raffaeleflorio.oogent.storage.document.TextDocuments;
import com.raffaeleflorio.oogent.storage.score.CosineSimilarityScore;
import com.raffaeleflorio.oogent.text.PlainText;

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
                                        new PlainText(entry.getKey()),
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
                        text,
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

    private record StoredText(Text text, Embedding embedding) {
    }
}
