package com.raffaeleflorio.oogent.inmemory;

import com.raffaeleflorio.oogent.Conversation;
import com.raffaeleflorio.oogent.Message;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public final class InMemoryConversation implements Conversation {

    private final String id;
    private final List<Message> messages;

    public InMemoryConversation() {
        this(UUID.randomUUID().toString());
    }

    public InMemoryConversation(final String id) {
        this(id, List.of());
    }

    InMemoryConversation(final String id, final List<Message> messages) {
        this.id = id;
        this.messages = messages;
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public Conversation append(final Message message) {
        return new InMemoryConversation(
                this.id,
                Stream.concat(this.messages.stream(), Stream.of(message)).toList()
        );
    }

    @Override
    public Stream<Message> stream() {
        return this.messages.stream();
    }
}
