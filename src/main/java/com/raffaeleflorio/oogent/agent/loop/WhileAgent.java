package com.raffaeleflorio.oogent.agent.loop;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.agent.TextResponse;

import java.util.function.Predicate;

public final class WhileAgent implements Agent {

    private final Predicate<? super Response> condition;
    private final Agent origin;

    public WhileAgent(final Predicate<? super Response> condition, final Agent origin) {
        this.condition = condition;
        this.origin = origin;
    }

    @Override
    public Response response(final Text text) {
        var result = this.origin.response(text);
        var tokenUsage = result.tokenUsage();
        while (this.condition.test(result)) {
            result = this.origin.response(text);
            tokenUsage = tokenUsage.sum(result.tokenUsage());
        }
        return new TextResponse(result, result.sources(), tokenUsage);
    }
}
