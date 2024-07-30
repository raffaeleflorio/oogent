package com.raffaeleflorio.oogent.storage.inmemory;

import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.RelevantText;
import com.raffaeleflorio.oogent.RelevantTexts;
import com.raffaeleflorio.oogent.Text;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

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
        return this.relevantTexts
                .stream()
                .<Text>map(Function.identity())
                .reduce(
                        new PlainText(""),
                        (left, right) -> left.then(prefix).then(right)
                );
    }

    @NotNull
    @Override
    public Iterator<RelevantText> iterator() {
        return this.relevantTexts.iterator();
    }
}
