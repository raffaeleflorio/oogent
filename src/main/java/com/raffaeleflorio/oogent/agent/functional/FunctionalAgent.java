package com.raffaeleflorio.oogent.agent.functional;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;

import java.util.function.Function;
import java.util.function.Supplier;

public final class FunctionalAgent implements Agent {

    private final Function<? super Text, ? extends Response> fn;

    public FunctionalAgent(final Agent origin, final Function<? super Response, ? extends Response> fn) {
        this(
                text -> fn.apply(origin.response(text))
        );
    }

    public FunctionalAgent(final Supplier<? extends Response> supplier) {
        this(x -> supplier.get());
    }

    public FunctionalAgent(final Function<? super Text, ? extends Response> fn) {
        this.fn = fn;
    }

    @Override
    public Response response(final Text text) {
        return this.fn.apply(text);
    }
}
