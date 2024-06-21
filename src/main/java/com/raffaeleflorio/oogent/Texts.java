package com.raffaeleflorio.oogent;

public interface Texts extends Iterable<Text> {

    Texts then(Text text); // TODO: mhn...

    Text joined(Text delimiter);

    Text listed();
}
