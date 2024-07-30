package com.raffaeleflorio.oogent;

public interface Storage {

    RelevantTexts relevantTexts(Text text, Double minScore, Integer limit);

    void store(Document document);

    void clear();

    void remove(Text id);
}
