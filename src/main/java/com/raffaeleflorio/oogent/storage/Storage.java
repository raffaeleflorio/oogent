package com.raffaeleflorio.oogent.storage;

import com.raffaeleflorio.oogent.Text;

public interface Storage {

    Documents documents(Text text, Integer limit, Double minScore);

    void store(Text id, Text text);

    void clear();

    void remove(Text id);
}
