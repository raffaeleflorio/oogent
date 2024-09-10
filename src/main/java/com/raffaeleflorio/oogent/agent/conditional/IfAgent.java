package com.raffaeleflorio.oogent.agent.conditional;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;

import java.util.function.Predicate;

public final class IfAgent implements Agent {

    private final Predicate<? super Text> condition;
    private final Agent trueAgent;
    private final Agent falseAgent;

    public IfAgent(final Predicate<? super Text> condition, final Agent trueAgent, final Agent falseAgent) {
        this.condition = condition;
        this.trueAgent = trueAgent;
        this.falseAgent = falseAgent;
    }

    @Override
    public Response response(final Text input) {
        return this.condition.test(input) ? this.trueAgent.response(input) : this.falseAgent.response(input);
    }
}
