package com.raffaeleflorio.oogent.agent.chain;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.agent.TextResponse;

import java.util.Arrays;
import java.util.List;

public final class ChainAgent implements Agent {

    private final List<? extends Agent> chain;

    public ChainAgent(final Agent... chain) {
        this(Arrays.asList(chain));
    }

    public ChainAgent(
            final List<? extends Agent> chain
    ) {
        this.chain = chain;
    }

    @Override
    public Response response(final Text text) {
        var response = this.chain.get(0).response(text);
        var sources = response.sources();
        var tokenUsage = response.tokenUsage();
        for (var i = 1; i < this.chain.size(); i++) {
            response = this.chain.get(i).response(response);
            sources = sources.merged(response.sources());
            tokenUsage = tokenUsage.sum(response.tokenUsage());
        }
        return new TextResponse(response, sources, tokenUsage);
    }
}
