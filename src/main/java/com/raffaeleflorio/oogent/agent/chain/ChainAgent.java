package com.raffaeleflorio.oogent.agent.chain;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Sources;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.text.response.TextResponse;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public final class ChainAgent implements Agent {

    private final List<? extends Agent> chain;
    private final BiFunction<? super Text, ? super Sources, ? extends Response> responseFn;

    public ChainAgent(final Agent... chain) {
        this(Arrays.asList(chain));
    }

    public ChainAgent(final List<? extends Agent> chain) {
        this(chain, TextResponse::new);
    }

    public ChainAgent(
            final BiFunction<? super Text, ? super Sources, ? extends Response> responseFn,
            final Agent... chain
    ) {
        this(Arrays.asList(chain), responseFn);
    }

    public ChainAgent(
            final List<? extends Agent> chain,
            final BiFunction<? super Text, ? super Sources, ? extends Response> responseFn
    ) {
        this.chain = chain;
        this.responseFn = responseFn;
    }

    @Override
    public Response response(final Text text) {
        var response = this.chain.get(0).response(text);
        var sources = response.sources();
        for (var i = 1; i < this.chain.size(); i++) {
            response = this.chain.get(i).response(response);
            sources = sources.merged(response.sources());
        }
        return this.responseFn.apply(response, sources);
    }
}
