package com.raffaeleflorio.oogent.conversation;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Conversation;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.simple.SimpleResponse;
import com.raffaeleflorio.oogent.simple.SimpleText;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ConversationAgent implements Agent {

    private final Conversation conversation;
    private final Text humanId;
    private final Text aiId;

    public ConversationAgent(final Conversation conversation, final String humanId, final String aiId) {
        this(conversation, new SimpleText(humanId), new SimpleText(aiId));
    }

    public ConversationAgent(final Conversation conversation, final Text humanId, final Text aiId) {
        this.conversation = conversation;
        this.humanId = humanId;
        this.aiId = aiId;
    }

    @Override
    public Response response(final Text text) {
        return new SimpleResponse(
                Stream.concat(
                        this.conversation.asList(this.humanId, this.aiId).stream().map(Text::text),
                        Stream.of(this.humanId.then(text).text())
                ).collect(Collectors.joining("\n"))
        );
    }
}
