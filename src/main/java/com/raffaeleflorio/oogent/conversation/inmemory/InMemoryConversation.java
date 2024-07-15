package com.raffaeleflorio.oogent.conversation.inmemory;

import com.raffaeleflorio.oogent.Conversation;
import com.raffaeleflorio.oogent.Message;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.text.Listed;
import com.raffaeleflorio.oogent.text.PlainText;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;

public final class InMemoryConversation implements Conversation {

    private final Text id;
    private final List<Message> messages;
    private final Function<List<Text>, Text> listedFn;

    public InMemoryConversation() {
        this(UUID.randomUUID().toString());
    }

    public InMemoryConversation(final String id) {
        this(new PlainText(id));
    }

    public InMemoryConversation(final Text id) {
        this(id, List.of(), Listed::new);
    }

    InMemoryConversation(final Text id, final List<Message> messages, final Function<List<Text>, Text> listedFn) {
        this.id = id;
        this.messages = messages;
        this.listedFn = listedFn;
    }


    @Override
    public Text id() {
        return this.id;
    }

    @Override
    public Conversation then(final Message message) {
        return new InMemoryConversation(
                this.id,
                Stream.concat(this.messages.stream(), Stream.of(message)).toList(),
                this.listedFn
        );
    }

    @Override
    public Text listed(final Text humanId, final Text aiId) {
        return this.listedFn.apply(
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
    public Conversation head(final Integer messages) {
        return this.subList(
                0,
                Math.min(messages, this.size())
        );
    }

    private Conversation subList(final Integer begin, final Integer end) {
        return new InMemoryConversation(
                this.id,
                this.messages.subList(begin, end),
                this.listedFn
        );
    }

    @Override
    public Conversation tail(final Integer messages) {
        return this.subList(
                this.size() - Math.min(messages, this.size()),
                this.size()
        );
    }

    @NotNull
    @Override
    public Iterator<Message> iterator() {
        return this.messages.iterator();
    }
}
