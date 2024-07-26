package com.raffaeleflorio.oogent.agent.conversational;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.agent.TextResponse;

import java.util.function.Function;

public final class ConversationalAgent implements Agent {

    private final Conversation conversation;
    private final Text humanId;
    private final Text aiId;
    private final Function<? super Text, ? extends Response> responseFn;
    private final Function<? super Text, ? extends Message> messageFn;

    public ConversationalAgent(final Conversation conversation, final String humanId, final String aiId) {
        this(conversation, new PlainText(humanId), new PlainText(aiId));
    }

    public ConversationalAgent(final Conversation conversation, final Text humanId, final Text aiId) {
        this(conversation, humanId, aiId, TextResponse::new, HumanMessage::new);
    }

    public ConversationalAgent(
            final Conversation conversation,
            final String humanId,
            final String aiId,
            final Function<? super Text, ? extends Response> responseFn,
            final Function<? super Text, ? extends Message> messageFn
    ) {
        this(conversation, new PlainText(humanId), new PlainText(aiId), responseFn, messageFn);
    }

    public ConversationalAgent(
            final Conversation conversation,
            final Text humanId,
            final Text aiId,
            final Function<? super Text, ? extends Response> responseFn,
            final Function<? super Text, ? extends Message> messageFn
    ) {
        this.conversation = conversation;
        this.humanId = humanId;
        this.aiId = aiId;
        this.responseFn = responseFn;
        this.messageFn = messageFn;
    }

    @Override
    public Response response(final Text text) {
        return this.responseFn.apply(
                this.conversation
                        .then(this.messageFn.apply(text))
                        .listed(this.humanId, this.aiId)
        );
    }
}
