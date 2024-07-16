package com.raffaeleflorio.oogent;

public interface Storage {

    Documents documents(Text text);

    void add(Text id, Text text);
}
