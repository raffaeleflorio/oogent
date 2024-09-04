package com.raffaeleflorio.oogent.agent.rag;

import com.raffaeleflorio.oogent.RelevantText;
import com.raffaeleflorio.oogent.RelevantTexts;
import com.raffaeleflorio.oogent.Source;
import com.raffaeleflorio.oogent.Sources;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.agent.TextSources;

import java.util.Iterator;
import java.util.stream.StreamSupport;

public final class RelevantTextsSources implements Sources {

    private final Sources origin;

    public RelevantTextsSources(final RelevantTexts relevantTexts) {
        this(
                new TextSources(
                        StreamSupport.stream(relevantTexts.spliterator(), false)
                                .map(RelevantText::source)
                                .toList()
                )
        );
    }

    public RelevantTextsSources(final Sources origin) {
        this.origin = origin;
    }

    @Override
    public Boolean empty() {
        return this.origin.empty();
    }

    @Override
    public Sources with(final Source source) {
        return this.origin.with(source);
    }

    @Override
    public Sources merged(final Sources sources) {
        return this.origin.merged(sources);
    }

    @Override
    public Boolean contains(final Text id) {
        return this.origin.contains(id);
    }

    @Override
    public Source source(final Text id) {
        return this.origin.source(id);
    }

    @Override
    public Iterator<Source> iterator() {
        return this.origin.iterator();
    }
}
