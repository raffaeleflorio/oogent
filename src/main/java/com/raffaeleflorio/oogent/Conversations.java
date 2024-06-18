package com.raffaeleflorio.oogent;

import java.util.Optional;

public interface Conversations {

    Optional<Conversation> conversation(Text id);

    Conversation conversation();

    void save(Conversation conversation);

    void delete(Text id);
}
