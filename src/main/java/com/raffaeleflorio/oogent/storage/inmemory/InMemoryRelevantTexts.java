package com.raffaeleflorio.oogent.storage.inmemory;

import com.raffaeleflorio.oogent.Listed;
import com.raffaeleflorio.oogent.RelevantText;
import com.raffaeleflorio.oogent.RelevantTexts;
import com.raffaeleflorio.oogent.Text;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

final class InMemoryRelevantTexts implements RelevantTexts {

    private final List<RelevantText> relevantTexts;

    InMemoryRelevantTexts(final List<RelevantText> relevantTexts) {
        this.relevantTexts = relevantTexts;
    }

    @Override
    public Integer size() {
        return this.relevantTexts.size();
    }

    @Override
    public Text listed(final Text prefix) {
        return new Listed(
                this.relevantTexts
                        .stream()
                        .map(prefix::then)
                        .toList()
        );
    }

    @NotNull
    @Override
    public Iterator<RelevantText> iterator() {
        return this.relevantTexts.iterator();
    }
}
