package com.raffaeleflorio.oogent;

public interface Tool {

    Signature signature();

    Text result(Text args);

    interface Signature {

        Text id();

        Text description();

        Text asText();
    }
}
