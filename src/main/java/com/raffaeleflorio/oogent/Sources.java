package com.raffaeleflorio.oogent;

public interface Sources extends Iterable<Source> {

    Boolean empty();

    Sources with(Source source);

    Sources merged(Sources sources);

    Boolean contains(Text id);

    Source source(Text id);
}
