package com.raffaeleflorio.oogent;

public interface Documents extends Iterable<Document> {

    Boolean contains(Text id);

    Document document(Text id);

    Integer size();

    Text listed(Text prefix);
}
