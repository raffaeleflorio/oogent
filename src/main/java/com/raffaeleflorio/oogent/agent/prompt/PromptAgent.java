package com.raffaeleflorio.oogent.agent.prompt;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.LLM;
import com.raffaeleflorio.oogent.PromptTemplate;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.text.response.TextResponse;

import java.util.function.Function;

public final class PromptAgent implements Agent {

    private final LLM llm;
    private final PromptTemplate promptTemplate;
    private final Function<Text, Response> responseFn;

    public PromptAgent(final LLM llm, final PromptTemplate promptTemplate) {
        this(llm, promptTemplate, TextResponse::new);
    }

    public PromptAgent(final LLM llm, final PromptTemplate promptTemplate, final Function<Text, Response> responseFn) {
        this.llm = llm;
        this.promptTemplate = promptTemplate;
        this.responseFn = responseFn;
    }

    @Override
    public Response response(final Text text) {
        return this.responseFn.apply(this.llm.completion(this.promptTemplate.prompt(text)));
    }
}
