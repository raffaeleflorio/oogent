package com.raffaeleflorio.oogent.storage;

import com.raffaeleflorio.oogent.Text;

public interface Documents extends Iterable<Document> {

    Boolean contains(Text id);

    Document document(Text id);

    Integer size();

    Text listed(Text prefix);
}
