package com.raffaeleflorio.oogent.agent.rag;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.RelevantTexts;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Storage;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.agent.TextResponse;

import java.util.function.Function;

public final class StorageAgent implements Agent {

    private final Storage storage;
    private final Double minScore;
    private final Integer limit;
    private final Function<? super RelevantTexts, ? extends Response> responseFn;

    public StorageAgent(
            final Storage storage,
            final Double minScore,
            final Integer limit
    ) {
        this(
                storage,
                minScore,
                limit,
                relevantTexts -> new TextResponse(
                        relevantTexts.listed(new PlainText("- ")),
                        new RelevantTextsSources(relevantTexts)
                )
        );
    }

    public StorageAgent(
            final Storage storage,
            final Double minScore,
            final Integer limit,
            final Function<? super RelevantTexts, ? extends Response> responseFn
    ) {
        this.storage = storage;
        this.minScore = minScore;
        this.limit = limit;
        this.responseFn = responseFn;
    }

    @Override
    public Response response(final Text text) {
        var relevantTexts = this.storage.relevantTexts(text, this.minScore, this.limit);
        return this.responseFn.apply(relevantTexts);
    }
}
