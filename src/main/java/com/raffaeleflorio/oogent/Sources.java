package com.raffaeleflorio.oogent;

public interface Sources extends Iterable<Source> {

    Boolean empty();

    Sources with(Source source);

    Sources merged(Sources sources);

    Source source(Text id);

    Boolean contains(Text id);
}
