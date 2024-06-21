package com.raffaeleflorio.oogent.agent.conversational;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Conversation;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.text.PlainText;
import com.raffaeleflorio.oogent.text.response.TextResponse;

import java.util.function.Function;

public final class ConversationalAgent implements Agent {

    private final Conversation conversation;
    private final Text humanId;
    private final Text aiId;
    private final Function<Text, Response> responseFn;

    public ConversationalAgent(final Conversation conversation, final String humanId, final String aiId) {
        this(conversation, new PlainText(humanId), new PlainText(aiId));
    }

    public ConversationalAgent(final Conversation conversation, final Text humanId, final Text aiId) {
        this(conversation, humanId, aiId, TextResponse::new);
    }

    public ConversationalAgent(
            final Conversation conversation,
            final String humanId,
            final String aiId,
            final Function<Text, Response> responseFn
    ) {
        this(conversation, new PlainText(humanId), new PlainText(aiId), responseFn);
    }

    public ConversationalAgent(
            final Conversation conversation,
            final Text humanId,
            final Text aiId,
            final Function<Text, Response> responseFn
    ) {
        this.conversation = conversation;
        this.humanId = humanId;
        this.aiId = aiId;
        this.responseFn = responseFn;
    }

    @Override
    public Response response(final Text text) {
        return this.responseFn.apply(
                this.conversation.asTexts(this.humanId, this.aiId)
                        .then(this.humanId.then(text))
                        .listed()
        );
    }
}
