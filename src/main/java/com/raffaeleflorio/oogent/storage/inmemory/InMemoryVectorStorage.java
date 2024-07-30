package com.raffaeleflorio.oogent.storage.inmemory;

import com.raffaeleflorio.oogent.Document;
import com.raffaeleflorio.oogent.Embedding;
import com.raffaeleflorio.oogent.EmbeddingModel;
import com.raffaeleflorio.oogent.RelevantText;
import com.raffaeleflorio.oogent.RelevantTexts;
import com.raffaeleflorio.oogent.Storage;
import com.raffaeleflorio.oogent.Text;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.stream.StreamSupport;

public final class InMemoryVectorStorage implements Storage {

    private final EmbeddingModel embeddingModel;
    private final BiFunction<? super Embedding, ? super Embedding, Double> scoreFn;
    private final Map<String, List<StoredText>> map;

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
            final Map<String, List<StoredText>> map
    ) {
        this.embeddingModel = embeddingModel;
        this.scoreFn = scoreFn;
        this.map = map;
    }

    @Override
    public RelevantTexts relevantTexts(final Text text, final Double minScore, final Integer limit) {
        var query = this.embeddingModel.embedding(text);
        return new InMemoryRelevantTexts(
                this.map
                        .values()
                        .stream()
                        .flatMap(Collection::stream)
                        .map(storedText -> this.relevantText(query, storedText))
                        .filter(doc -> doc.score() >= minScore)
                        .sorted(Comparator.comparing(RelevantText::score))
                        .limit(limit)
                        .toList()
        );
    }

    private RelevantText relevantText(final Embedding query, final StoredText storedText) {
        return new InMemoryRelevantText(
                storedText.chunk(),
                this.scoreFn.apply(query, storedText.embedding()),
                storedText.source()
        );
    }

    @Override
    public void store(final Document document) {
        this.map.put(
                document.id().asString(),
                this.storedTexts(document)
        );
    }

    private List<StoredText> storedTexts(final Document document) {
        return StreamSupport.stream(document.chunks().spliterator(), false)
                .map(chunk -> new StoredText(
                                document,
                                chunk,
                                this.embeddingModel.embedding(chunk)
                        )
                )
                .toList();
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public void remove(final Text id) {
        this.map.remove(id.asString());
    }

    private record StoredText(Document source, Text chunk, Embedding embedding) {
    }
}
