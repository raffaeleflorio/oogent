package com.raffaeleflorio.oogent;

public interface Text {

    Text then(Text text);

    Boolean contains(Text subText);

    Text afterFirst(Text subText);

    Text afterLast(Text subText);

    Text beforeFirst(Text subText);

    Text beforeLast(Text subText);

    Boolean startsWith(Text prefix);

    Boolean blank();

    Integer size();

    Text sub(Integer start, Integer endExcluded);

    String asString();
}
