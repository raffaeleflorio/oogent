package com.raffaeleflorio.oogent;

import java.util.Optional;

public interface Conversations {

    Optional<Conversation> conversation(Text id);

    void save(Conversation conversation);

    void delete(Text id);
}
