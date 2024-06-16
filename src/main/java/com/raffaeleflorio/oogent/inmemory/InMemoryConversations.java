package com.raffaeleflorio.oogent.inmemory;

import com.raffaeleflorio.oogent.Conversation;
import com.raffaeleflorio.oogent.Conversations;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryConversations implements Conversations {

    private final Map<String, Conversation> conversations;

    public InMemoryConversations() {
        this(new ConcurrentHashMap<>());
    }

    InMemoryConversations(final Map<String, Conversation> conversations) {
        this.conversations = conversations;
    }

    @Override
    public Optional<Conversation> conversation(final String id) {
        return Optional.ofNullable(this.conversations.get(id));
    }

    @Override
    public void save(final Conversation conversation) {
        this.conversations.put(conversation.id(), conversation);
    }

    @Override
    public void delete(final String id) {
        this.conversations.remove(id);
    }
}
