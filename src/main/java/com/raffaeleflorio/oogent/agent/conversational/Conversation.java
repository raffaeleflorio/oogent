package com.raffaeleflorio.oogent.agent.conversational;

import com.raffaeleflorio.oogent.Text;

public interface Conversation extends Iterable<Message> {

    Text id();

    Conversation then(Message message);

    Text listed(Text humanId, Text aiId);

    Integer size();

    Conversation head(Integer messages);

    Conversation tail(Integer messages);
}
