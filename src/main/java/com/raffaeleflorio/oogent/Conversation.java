package com.raffaeleflorio.oogent;

public interface Conversation {

    Text id();

    Conversation append(Message message);

    Texts asTexts(Text humanId, Text aiId);
}
