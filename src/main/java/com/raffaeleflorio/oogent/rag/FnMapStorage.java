package com.raffaeleflorio.oogent.rag;

import com.raffaeleflorio.oogent.Storage;
import com.raffaeleflorio.oogent.Text;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public final class FnMapStorage implements Storage {

    private final Map<Predicate<Text>, List<Text>> map;

    public FnMapStorage(final Map<Predicate<Text>, List<Text>> map) {
        this.map = map;
    }

    @Override
    public List<Text> output(final Text text) {
        return this.map
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().test(text))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(List.of());
    }
}
