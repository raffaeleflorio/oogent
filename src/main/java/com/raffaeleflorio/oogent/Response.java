package com.raffaeleflorio.oogent;

public interface Response extends Text {

    @Override
    Response then(Text text);

    @Override
    Response afterFirst(Text text);

    @Override
    Response afterLast(Text text);

    @Override
    Response beforeFirst(Text text);

    @Override
    Response beforeLast(Text text);
}
