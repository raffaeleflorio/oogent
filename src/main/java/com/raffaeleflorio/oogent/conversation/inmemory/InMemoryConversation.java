package com.raffaeleflorio.oogent.conversation.inmemory;

import com.raffaeleflorio.oogent.Conversation;
import com.raffaeleflorio.oogent.Message;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.Texts;
import com.raffaeleflorio.oogent.text.OrderedTexts;
import com.raffaeleflorio.oogent.text.PlainText;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

final class InMemoryConversation implements Conversation {

    private final Text id;
    private final List<Message> messages;

    InMemoryConversation() {
        this(UUID.randomUUID().toString());
    }

    InMemoryConversation(final String id) {
        this(new PlainText(id));
    }

    InMemoryConversation(final Text id) {
        this(id, List.of());
    }

    InMemoryConversation(final Text id, final List<Message> messages) {
        this.id = id;
        this.messages = messages;
    }

    @Override
    public Text id() {
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
    public Texts asTexts(final Text humanId, final Text aiId) {
        return new OrderedTexts(
                this.messages
                        .stream()
                        .map(message -> message.human() ? humanId.then(message) : aiId.then(message))
                        .toList()
        );
    }
}
