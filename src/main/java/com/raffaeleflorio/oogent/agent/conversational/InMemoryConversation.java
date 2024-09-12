package com.raffaeleflorio.oogent.agent.conversational;

import com.raffaeleflorio.oogent.Listed;
import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.Text;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public final class InMemoryConversation implements Conversation {

    private final Text id;
    private final List<Message> messages;

    public InMemoryConversation() {
        this(UUID.randomUUID().toString());
    }

    public InMemoryConversation(final String id) {
        this(new PlainText(id));
    }

    public InMemoryConversation(final Text id) {
        this(id, List.of());
    }

    public InMemoryConversation(final Text id, final List<Message> messages) {
        this.id = id;
        this.messages = messages;
    }


    @Override
    public Text id() {
        return this.id;
    }

    @Override
    public Conversation then(final Message message) {
        return new InMemoryConversation(
                this.id,
                Stream.concat(this.messages.stream(), Stream.of(message)).toList()
        );
    }

    @Override
    public Text listed(final Text humanId, final Text aiId) {
        return new Listed(
                this.messages
                        .stream()
                        .map(message -> message.human() ? humanId.then(message) : aiId.then(message))
                        .toList()
        );
    }

    @Override
    public Integer size() {
        return this.messages.size();
    }

    @Override
    public Conversation head(final Integer nMessages) {
        return this.subList(
                0,
                Math.min(nMessages, this.size())
        );
    }

    private Conversation subList(final Integer begin, final Integer end) {
        return new InMemoryConversation(
                this.id,
                this.messages.subList(begin, end)
        );
    }

    @Override
    public Conversation tail(final Integer nMessages) {
        return this.subList(
                this.size() - Math.min(nMessages, this.size()),
                this.size()
        );
    }

    @Override
    public Iterator<Message> iterator() {
        return this.messages.iterator();
    }
}
