package com.raffaeleflorio.oogent.agent.echo;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.text.response.TextResponse;

public final class EchoAgent implements Agent {

    @Override
    public Response response(final Text text) {
        return new TextResponse(text);
    }
}
