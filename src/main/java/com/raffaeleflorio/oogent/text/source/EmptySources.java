package com.raffaeleflorio.oogent.text.source;

import com.raffaeleflorio.oogent.Source;
import com.raffaeleflorio.oogent.Sources;
import com.raffaeleflorio.oogent.Text;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public final class EmptySources implements Sources {

    private final Sources origin;

    public EmptySources() {
        this(new TextSources(List.of()));
    }

    EmptySources(final Sources origin) {
        this.origin = origin;
    }

    @Override
    public Boolean empty() {
        return this.origin.empty();
    }

    @Override
    public Sources with(final Source source) {
        return this.origin.with(source);
    }

    @Override
    public Sources merged(final Sources sources) {
        return this.origin.merged(sources);
    }

    @Override
    public Optional<Source> source(final Text id) {
        return Optional.empty();
    }

    @Override
    public Boolean contains(final Text id) {
        return false;
    }

    @NotNull
    @Override
    public Iterator<Source> iterator() {
        return this.origin.iterator();
    }
}
