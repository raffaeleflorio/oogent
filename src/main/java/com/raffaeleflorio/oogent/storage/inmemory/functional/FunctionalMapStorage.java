package com.raffaeleflorio.oogent.storage.inmemory.functional;

import com.raffaeleflorio.oogent.Storage;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.Texts;
import com.raffaeleflorio.oogent.text.OrderedTexts;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public final class FunctionalMapStorage implements Storage {

    private final Map<Predicate<Text>, List<Text>> map;

    public FunctionalMapStorage(final Map<Predicate<Text>, List<Text>> map) {
        this.map = map;
    }

    @Override
    public Texts output(final Text text) {
        return new OrderedTexts(
                this.map
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getKey().test(text))
                        .findFirst()
                        .map(Map.Entry::getValue)
                        .orElse(List.of())
        );
    }
}
