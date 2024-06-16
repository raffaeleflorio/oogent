package com.raffaeleflorio.oogent;

import java.util.Optional;

public interface Conversations {

    Optional<Conversation> conversation(String id);

    void save(Conversation conversation);

    void delete(String id);
}
