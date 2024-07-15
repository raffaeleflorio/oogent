package com.raffaeleflorio.oogent;

public interface Conversation extends Iterable<Message> {

    Text id();

    Conversation then(Message message);

    Text listed(Text humanId, Text aiId);

    Integer size();

    Conversation head(Integer messages);

    Conversation tail(Integer messages);
}
