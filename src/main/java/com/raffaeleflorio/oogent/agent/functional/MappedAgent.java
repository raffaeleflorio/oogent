package com.raffaeleflorio.oogent.agent.functional;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;

import java.util.function.Function;

public final class MappedAgent implements Agent {

    private final Agent origin;

    public MappedAgent(final Agent origin, final Function<? super Response, ? extends Response> fn) {
        this(
                new FunctionalAgent(
                        text -> fn.apply(origin.response(text))
                )
        );
    }

    MappedAgent(final Agent origin) {
        this.origin = origin;
    }

    @Override
    public Response response(final Text text) {
        return this.origin.response(text);
    }
}
