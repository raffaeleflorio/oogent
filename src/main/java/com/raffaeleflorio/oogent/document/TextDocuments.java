package com.raffaeleflorio.oogent.document;

import com.raffaeleflorio.oogent.Document;
import com.raffaeleflorio.oogent.Documents;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.text.Listed;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class TextDocuments implements Documents {

    private final Map<String, Document> documents;
    private final Function<List<Text>, Text> listedFn;

    public TextDocuments(final Collection<Document> documents) {
        this(
                documents
                        .stream()
                        .collect(Collectors.toUnmodifiableMap(doc -> doc.id().asString(), Function.identity())),
                Listed::new
        );
    }

    TextDocuments(final Map<String, Document> documents, final Function<List<Text>, Text> listedFn) {
        this.documents = documents;
        this.listedFn = listedFn;
    }

    @Override
    public Boolean contains(final Text id) {
        return this.documents.containsKey(id.asString());
    }

    @Override
    public Document document(final Text id) {
        return this.documents.getOrDefault(
                id.asString(),
                new MissingDocument(id)
        );
    }

    @Override
    public Integer size() {
        return this.documents.size();
    }

    @Override
    public Text listed(final Text prefix) {
        return this.listedFn.apply(
                this.documents
                        .values()
                        .stream()
                        .map(document -> prefix.then(document.text()))
                        .toList()
        );
    }

    @NotNull
    @Override
    public Iterator<Document> iterator() {
        return this.documents.values().iterator();
    }
}
