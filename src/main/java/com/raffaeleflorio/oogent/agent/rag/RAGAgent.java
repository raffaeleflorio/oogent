package com.raffaeleflorio.oogent.agent.rag;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.LLM;
import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.PromptTemplate;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Sources;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.agent.TextResponse;

import java.util.Map;
import java.util.function.BiFunction;

public final class RAGAgent implements Agent {

    private final Agent contextAgent;
    private final LLM llm;
    private final PromptTemplate promptTemplate;
    private final BiFunction<? super Text, ? super Sources, ? extends Response> responseFn;

    public RAGAgent(
            final Agent contextAgent,
            final LLM llm,
            final PromptTemplate promptTemplate
    ) {
        this(
                contextAgent,
                llm,
                promptTemplate,
                TextResponse::new
        );
    }

    public RAGAgent(
            final Agent contextAgent,
            final LLM llm,
            final PromptTemplate promptTemplate,
            final BiFunction<? super Text, ? super Sources, ? extends Response> responseFn
    ) {
        this.contextAgent = contextAgent;
        this.llm = llm;
        this.promptTemplate = promptTemplate;
        this.responseFn = responseFn;
    }

    @Override
    public Response response(final Text text) {
        var context = this.contextAgent.response(text);
        return this.responseFn.apply(
                this.llm.completion(
                        this.promptTemplate.prompt(
                                Map.of(
                                        new PlainText("text"), text,
                                        new PlainText("context"), context
                                )
                        )
                ),
                context.sources()
        );
    }
}
