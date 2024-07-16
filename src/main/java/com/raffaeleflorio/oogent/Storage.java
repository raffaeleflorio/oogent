package com.raffaeleflorio.oogent;

public interface Storage {

    Documents documents(Text text);

    void store(Text id, Text text);
}
