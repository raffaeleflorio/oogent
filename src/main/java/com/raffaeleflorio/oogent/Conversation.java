package com.raffaeleflorio.oogent;

public interface Conversation {

    Text id();

    Conversation append(Message message);

    Texts asTexts(Text humanId, Text aiId);

    Integer size();

    Conversation head(Integer messages);

    Conversation tail(Integer messages);
}
