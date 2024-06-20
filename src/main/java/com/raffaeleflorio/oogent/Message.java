package com.raffaeleflorio.oogent;

public interface Message extends Text {

    Boolean ai();

    Boolean human();

    @Override
    Message then(Text text);

    @Override
    Message afterFirst(Text text);

    @Override
    Message afterLast(Text text);

    @Override
    Message beforeFirst(Text text);

    @Override
    Message beforeLast(Text text);
}
