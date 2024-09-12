package com.raffaeleflorio.oogent;

public interface Tool {

    Text id();

    Text signature();

    Text result(Text args);
}
