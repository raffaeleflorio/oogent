package com.raffaeleflorio.oogent.conversation;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Conversation;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.simple.SimpleResponse;
import com.raffaeleflorio.oogent.simple.SimpleText;

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
                this.conversation.asTexts(this.humanId, this.aiId)
                        .then(this.humanId.then(text))
                        .listed()
        );
    }
}
