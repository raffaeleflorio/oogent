package com.raffaeleflorio.oogent.agent.rag;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Documents;
import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Storage;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.agent.TextResponse;

import java.util.function.Function;

public final class StorageAgent implements Agent {

    private final Storage storage;
    private final Integer limit;
    private final Double minScore;
    private final Function<? super Documents, ? extends Response> responseFn;

    public StorageAgent(
            final Storage storage,
            final Integer limit,
            final Double minScore
    ) {
        this(
                storage,
                limit,
                minScore,
                documents -> new TextResponse(
                        documents.listed(new PlainText("-")),
                        new DocumentsSources(documents)
                )
        );
    }

    public StorageAgent(
            final Storage storage,
            final Integer limit,
            final Double minScore,
            final Function<? super Documents, ? extends Response> responseFn
    ) {
        this.storage = storage;
        this.limit = limit;
        this.minScore = minScore;
        this.responseFn = responseFn;
    }

    @Override
    public Response response(final Text text) {
        var documents = this.storage.documents(text, this.limit, this.minScore);
        return this.responseFn.apply(documents);
    }
}
