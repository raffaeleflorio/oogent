package com.raffaeleflorio.oogent;

public interface RelevantTexts extends Iterable<RelevantText> {

    Integer size();

    Text listed(Text prefix);
}
