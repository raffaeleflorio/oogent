package com.raffaeleflorio.oogent;

public interface Response extends Text {

    Sources sources();

    TokenUsage tokenUsage();
}
