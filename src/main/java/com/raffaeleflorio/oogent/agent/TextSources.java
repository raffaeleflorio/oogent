package com.raffaeleflorio.oogent.agent;

import com.raffaeleflorio.oogent.Source;
import com.raffaeleflorio.oogent.Sources;
import com.raffaeleflorio.oogent.Text;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class TextSources implements Sources {

    private final Map<String, Source> sources;

    public TextSources(final Source source) {
        this(List.of(source));
    }

    public TextSources(final Collection<Source> sources) {
        this(
                sources
                        .stream()
                        .collect(
                                Collectors.toMap(
                                        source -> source.id().asString(),
                                        Function.identity(),
                                        (left, ignored) -> left
                                )
                        )
        );
    }

    TextSources(final Map<String, Source> sources) {
        this.sources = sources;
    }

    @Override
    public Boolean empty() {
        return this.sources.isEmpty();
    }

    @Override
    public Sources with(final Source source) {
        return new TextSources(
                Stream.concat(this.sources.values().stream(), Stream.of(source)).toList()
        );
    }

    @Override
    public Sources merged(final Sources sources) {
        return new TextSources(
                Stream.concat(this.sources.values().stream(), this.stream(sources)).toList()
        );
    }

    private Stream<Source> stream(final Sources sources) {
        return StreamSupport.stream(sources.spliterator(), false);
    }

    @Override
    public Boolean contains(final Text id) {
        return this.sources.containsKey(id.asString());
    }

    @Override
    public Source source(final Text id) {
        return this.sources.getOrDefault(
                id.asString(),
                new MissingSource(id)
        );
    }

    @Override
    public Iterator<Source> iterator() {
        return this.sources.values().iterator();
    }
}
