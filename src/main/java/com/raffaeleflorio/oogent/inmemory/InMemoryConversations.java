package com.raffaeleflorio.oogent.inmemory;

import com.raffaeleflorio.oogent.Conversation;
import com.raffaeleflorio.oogent.Conversations;
import com.raffaeleflorio.oogent.Text;

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
    public Optional<Conversation> conversation(final Text id) {
        return Optional.ofNullable(this.conversations.get(id.asString()));
    }

    @Override
    public Conversation conversation() {
        var result = new InMemoryConversation();
        this.save(result);
        return result;
    }

    @Override
    public void save(final Conversation conversation) {
        this.conversations.put(conversation.id().asString(), conversation);
    }

    @Override
    public void delete(final Text id) {
        this.conversations.remove(id.asString());
    }
}
