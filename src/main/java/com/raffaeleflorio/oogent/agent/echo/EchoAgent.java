package com.raffaeleflorio.oogent.agent.echo;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.text.response.TextResponse;

import java.util.function.Function;

public final class EchoAgent implements Agent {

    private final Function<Text, Response> responseFn;

    public EchoAgent() {
        this(TextResponse::new);
    }

    public EchoAgent(final Function<Text, Response> responseFn) {
        this.responseFn = responseFn;
    }

    @Override
    public Response response(final Text text) {
        return this.responseFn.apply(text);
    }
}
