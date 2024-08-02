package com.raffaeleflorio.oogent.text;

import com.raffaeleflorio.oogent.Text;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

public final class Cached implements Text {

    private final Supplier<Text> onMiss;
    private final ConcurrentMap<Integer, Text> cached;

    public Cached(final Text origin) {
        this(() -> origin.sub(0, origin.size()));
    }

    public Cached(final Supplier<Text> onMiss) {
        this(onMiss, new ConcurrentHashMap<>());
    }

    Cached(final Supplier<Text> onMiss, final ConcurrentMap<Integer, Text> cached) {
        this.onMiss = onMiss;
        this.cached = cached;
    }

    @Override
    public Text then(final Text text) {
        return this.cached().then(text);
    }

    private Text cached() {
        return this.cached.computeIfAbsent(0, x -> this.onMiss.get());
    }

    @Override
    public Boolean contains(final Text text) {
        return this.cached().contains(text);
    }

    @Override
    public Text afterFirst(final Text text) {
        return this.cached().afterFirst(text);
    }

    @Override
    public Text afterLast(final Text text) {
        return this.cached().afterLast(text);
    }

    @Override
    public Text beforeFirst(final Text text) {
        return this.cached().beforeFirst(text);
    }

    @Override
    public Text beforeLast(final Text text) {
        return this.cached().beforeLast(text);
    }

    @Override
    public Boolean startsWith(final Text prefix) {
        return this.cached().startsWith(prefix);
    }

    @Override
    public Boolean blank() {
        return this.cached().blank();
    }

    @Override
    public Integer size() {
        return this.cached().size();
    }

    @Override
    public Text sub(final Integer start, final Integer endExcluded) {
        return this.cached().sub(start, endExcluded);
    }

    @Override
    public String asString() {
        return this.cached().asString();
    }
}
