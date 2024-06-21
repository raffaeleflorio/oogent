package com.raffaeleflorio.oogent;

import java.util.Optional;

public interface Sources extends Iterable<Source> {

    Boolean empty();

    Sources with(Source source);

    Sources merged(Sources sources);

    Optional<Source> source(Text id);

    Boolean contains(Text id);
}
