package com.raffaeleflorio.oogent.agent.exception;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.agent.TextResponse;

import java.util.function.BiFunction;

public final class ExceptionalAgent implements Agent {

    private final Agent origin;
    private final BiFunction<? super Text, ? super Exception, Response> responseFn;

    public ExceptionalAgent(final Agent origin, final Text response) {
        this(origin, new TextResponse(response));
    }

    public ExceptionalAgent(final Agent origin, final Response response) {
        this(origin, (exception, text) -> response);
    }

    public ExceptionalAgent(
            final Agent origin,
            final BiFunction<? super Text, ? super Exception, Response> responseFn
    ) {
        this.origin = origin;
        this.responseFn = responseFn;
    }


    @Override
    public Response response(final Text input) {
        try {
            return this.origin.response(input);
        } catch (Exception e) {
            return this.responseFn.apply(input, e);
        }
    }
}
