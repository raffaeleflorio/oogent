package com.raffaeleflorio.oogent.agent.constant;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;

public final class ConstAgent implements Agent {

    private final Response response;

    public ConstAgent(final Response response) {
        this.response = response;
    }

    @Override
    public Response response(final Text text) {
        return this.response;
    }
}
