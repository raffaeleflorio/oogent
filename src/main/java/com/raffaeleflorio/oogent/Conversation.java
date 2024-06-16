package com.raffaeleflorio.oogent;

import java.util.List;

public interface Conversation {

    String id();

    Conversation append(Message message);

    List<Text> asList(Text humanId, Text agentId);
}
