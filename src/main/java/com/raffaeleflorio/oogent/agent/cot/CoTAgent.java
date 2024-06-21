package com.raffaeleflorio.oogent.agent.cot;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.LLM;
import com.raffaeleflorio.oogent.PromptTemplate;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.agent.chain.ChainAgent;
import com.raffaeleflorio.oogent.agent.functional.FunctionalAgent;
import com.raffaeleflorio.oogent.agent.prompt.PromptAgent;
import com.raffaeleflorio.oogent.text.response.TextResponse;

public final class CoTAgent implements Agent {

    private final Agent origin;

    public CoTAgent(final LLM llm, final PromptTemplate promptTemplate, final Text endResultKeyword) {
        this(
                new ChainAgent(
                        new PromptAgent(llm, promptTemplate),
                        new FunctionalAgent(
                                text -> new TextResponse(text.afterLast(endResultKeyword))
                        )
                )
        );
    }

    CoTAgent(final Agent origin) {
        this.origin = origin;
    }

    @Override
    public Response response(final Text text) {
        return this.origin.response(text);
    }
}
