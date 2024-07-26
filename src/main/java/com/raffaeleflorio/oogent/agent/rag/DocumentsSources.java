package com.raffaeleflorio.oogent.agent.rag;

import com.raffaeleflorio.oogent.Documents;
import com.raffaeleflorio.oogent.Source;
import com.raffaeleflorio.oogent.Sources;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.agent.TextSource;
import com.raffaeleflorio.oogent.agent.TextSources;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class DocumentsSources implements Sources {

    private final Documents documents;

    public DocumentsSources(final Documents documents) {
        this.documents = documents;
    }

    @Override
    public Boolean empty() {
        return this.documents.size() == 0;
    }

    @Override
    public Sources with(final Source source) {
        return new TextSources(this.stream().toList()).with(source);
    }

    private Stream<Source> stream() {
        return StreamSupport.stream(this.documents.spliterator(), false)
                .map(doc -> new TextSource(doc.id(), doc.text()));
    }

    @Override
    public Sources merged(final Sources sources) {
        return new TextSources(this.stream().toList()).merged(sources);
    }

    @Override
    public Boolean contains(final Text id) {
        return this.documents.contains(id);
    }

    @Override
    public Source source(final Text id) {
        var doc = this.documents.document(id);
        return new TextSource(doc.id(), doc.text());
    }

    @NotNull
    @Override
    public Iterator<Source> iterator() {
        return this.stream().iterator();
    }
}
