package com.raffaeleflorio.oogent.echo;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;

public final class EchoAgent implements Agent {

    private final Response response;

    public EchoAgent(final Response response) {
        this.response = response;
    }

    @Override
    public Response response(final Text text) {
        return this.response;
    }
}
