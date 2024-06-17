package com.raffaeleflorio.oogent;

public interface Text {

    String asString();

    Text then(Text text);

    Boolean contains(Text text);

    Text afterLast(Text text);

    Text beforeFirst(Text text);
}
