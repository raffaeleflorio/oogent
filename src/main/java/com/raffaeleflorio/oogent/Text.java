package com.raffaeleflorio.oogent;

public interface Text {

    Text then(Text text);

    Boolean contains(Text text);

    Text afterFirst(Text text);

    Text afterLast(Text text);

    Text beforeFirst(Text text);

    Text beforeLast(Text text);

    Boolean startsWith(Text prefix);

    Boolean blank();

    Integer size();

    Text sub(Integer start, Integer endExcluded);

    String asString();
}
