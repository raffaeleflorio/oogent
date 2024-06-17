package com.raffaeleflorio.oogent.function;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;

import java.util.function.Function;
import java.util.function.Supplier;

public final class FunctionAgent implements Agent {

    private final Function<Text, Response> fn;

    public FunctionAgent(final Supplier<Response> supplier) {
        this(x -> supplier.get());
    }

    public FunctionAgent(final Function<Text, Response> fn) {
        this.fn = fn;
    }

    @Override
    public Response response(final Text text) {
        return this.fn.apply(text);
    }
}
