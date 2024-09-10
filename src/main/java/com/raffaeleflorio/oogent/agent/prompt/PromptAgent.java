package com.raffaeleflorio.oogent.agent.prompt;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.LLM;
import com.raffaeleflorio.oogent.PromptTemplate;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.agent.TextResponse;

public final class PromptAgent implements Agent {

    private final LLM llm;
    private final PromptTemplate promptTemplate;

    public PromptAgent(
            final LLM llm,
            final PromptTemplate promptTemplate
    ) {
        this.llm = llm;
        this.promptTemplate = promptTemplate;
    }

    @Override
    public Response response(final Text input) {
        return new TextResponse(
                this.llm.completion(this.promptTemplate.prompt(input))
        );
    }
}
