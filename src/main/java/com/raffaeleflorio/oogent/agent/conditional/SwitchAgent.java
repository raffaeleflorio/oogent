package com.raffaeleflorio.oogent.agent.conditional;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public final class SwitchAgent implements Agent {

    private final List<Case> cases;

    public SwitchAgent(final Case... cases) {
        this(Arrays.asList(cases));
    }

    public SwitchAgent(final List<Case> cases) {
        this.cases = cases;
    }

    @Override
    public Response response(final Text text) {
        return this.cases
                .stream()
                .filter(aCase -> aCase.condition.test(text))
                .findFirst()
                .map(aCase -> aCase.agent)
                .map(agent -> agent.response(text))
                .orElseThrow(() -> new IllegalArgumentException("I'm unable to build a response. There should be a default case!"));
    }

    public final static class Case {

        private final Predicate<Text> condition;

        private final Agent agent;

        public Case(final Predicate<Text> condition, final Agent agent) {
            this.condition = condition;
            this.agent = agent;
        }
    }
}
