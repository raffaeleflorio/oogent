package com.raffaeleflorio.oogent;

public interface Document {

    Text id();

    Text text();

    Chunks chunks();

    interface Chunks extends Iterable<Text> {
    }
}
