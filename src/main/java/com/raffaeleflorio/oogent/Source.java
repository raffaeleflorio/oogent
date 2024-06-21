package com.raffaeleflorio.oogent;

public interface Source extends Text {

    Text id();

    @Override
    Source then(Text text);

    @Override
    Source afterFirst(Text text);

    @Override
    Source afterLast(Text text);

    @Override
    Source beforeFirst(Text text);

    @Override
    Source beforeLast(Text text);
}
