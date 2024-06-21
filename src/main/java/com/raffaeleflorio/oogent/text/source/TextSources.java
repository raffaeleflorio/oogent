package com.raffaeleflorio.oogent.text.source;

import com.raffaeleflorio.oogent.Source;
import com.raffaeleflorio.oogent.Sources;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class TextSources implements Sources {

    private final Collection<Source> sources;

    public TextSources(final Source source) {
        this(List.of(source));
    }

    public TextSources(final Collection<Source> sources) {
        this.sources = sources;
    }

    @Override
    public Boolean empty() {
        return this.sources.isEmpty();
    }

    @Override
    public Sources with(final Source source) {
        return new TextSources(
                Stream.concat(this.sources.stream(), Stream.of(source)).toList()
        );
    }

    @Override
    public Sources merged(final Sources sources) {
        return new TextSources(
                Stream.concat(this.sources.stream(), this.stream(sources)).toList()
        );
    }

    private Stream<Source> stream(final Sources sources) {
        return StreamSupport.stream(sources.spliterator(), false);
    }

    @NotNull
    @Override
    public Iterator<Source> iterator() {
        return this.sources.iterator();
    }
}
