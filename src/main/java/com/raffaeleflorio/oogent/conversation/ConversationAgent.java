package com.raffaeleflorio.oogent.conversation;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Conversation;
import com.raffaeleflorio.oogent.Message;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.simple.SimpleResponse;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ConversationAgent implements Agent {

    private final Conversation conversation;
    private final String humanId;
    private final String aiId;

    public ConversationAgent(final Conversation conversation, final String humanId, final String aiId) {
        this.conversation = conversation;
        this.humanId = humanId;
        this.aiId = aiId;
    }

    @Override
    public Response response(final Text text) {
        return new SimpleResponse(
                Stream.concat(
                        this.conversation.stream().map(this::asString),
                        Stream.of(this.humanId.concat(": ").concat(text.text()))
                ).collect(Collectors.joining("\n"))
        );
    }

    private String asString(final Message message) {
        if (message.ai()) {
            return this.aiId.concat(": ").concat(message.text());
        }
        return this.humanId.concat(": ").concat(message.text());
    }
}
