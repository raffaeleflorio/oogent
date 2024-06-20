package com.raffaeleflorio.oogent.agent.chain;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;

import java.util.Arrays;
import java.util.List;

public final class ChainAgent implements Agent {

    private final List<Agent> chain;

    public ChainAgent(final Agent... chain) {
        this(Arrays.asList(chain));
    }

    public ChainAgent(final List<Agent> chain) {
        this.chain = chain;
    }

    @Override
    public Response response(final Text text) {
        var result = this.chain.get(0).response(text);
        for (var i = 1; i < this.chain.size(); i++) {
            result = this.chain.get(i).response(result);
        }
        return result;
    }
}
