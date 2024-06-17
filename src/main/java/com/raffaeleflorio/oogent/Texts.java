package com.raffaeleflorio.oogent;

public interface Texts extends Iterable<Text> {

    Texts then(Text text);

    Text joined(Text delimiter);

    Text listed();
}
