package com.raffaeleflorio.oogent.agent.action;

import com.raffaeleflorio.oogent.Action;
import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.text.PlainText;

public final class ActionAgent implements Action {

    private final Agent origin;
    private final Text id;
    private final Text description;

    public ActionAgent(final Agent origin, final String id, final String description) {
        this(
                origin,
                new PlainText(id),
                new PlainText(description)
        );
    }

    public ActionAgent(final Agent origin, final Text id, final Text description) {
        this.origin = origin;
        this.id = id;
        this.description = description;
    }

    @Override
    public Response response(final Text text) {
        return this.origin.response(text);
    }

    @Override
    public Text id() {
        return this.id;
    }

    @Override
    public Text description() {
        return this.description;
    }
}
