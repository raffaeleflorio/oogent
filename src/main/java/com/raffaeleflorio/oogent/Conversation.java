package com.raffaeleflorio.oogent;

import java.util.stream.Stream;

public interface Conversation {

    String id();

    Conversation append(Message message);

    Stream<Message> stream(); // TODO: simplify with List<Text> asList(fn)
}
