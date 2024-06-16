package com.raffaeleflorio.oogent.rag;

import com.raffaeleflorio.oogent.Storage;
import com.raffaeleflorio.oogent.Text;

import java.util.List;
import java.util.Map;

public final class MapStorage implements Storage {

    private final Map<String, List<Text>> map;

    public MapStorage(final Map<String, List<Text>> map) {
        this.map = map;
    }

    @Override
    public List<Text> output(final Text text) {
        return this.map.getOrDefault(text.text(), List.of());
    }
}
