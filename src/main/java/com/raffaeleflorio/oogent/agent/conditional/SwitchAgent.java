package com.raffaeleflorio.oogent.agent.conditional;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public final class SwitchAgent implements Agent {

    private final Agent defaultAgent;
    private final List<Case> cases;

    public SwitchAgent(final Agent defaultAgent, final Case... cases) {
        this(defaultAgent, Arrays.asList(cases));
    }

    public SwitchAgent(final Agent defaultAgent, final List<Case> cases) {
        this.defaultAgent = defaultAgent;
        this.cases = cases;
    }

    @Override
    public Response response(final Text input) {
        return this.cases
                .stream()
                .filter(aCase -> aCase.condition.test(input))
                .findFirst()
                .map(aCase -> aCase.agent)
                .map(agent -> agent.response(input))
                .orElseGet(() -> this.defaultAgent.response(input));
    }

    public final static class Case {

        private final Predicate<? super Text> condition;

        private final Agent agent;

        public Case(final Predicate<? super Text> condition, final Agent agent) {
            this.condition = condition;
            this.agent = agent;
        }
    }
}
