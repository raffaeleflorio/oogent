package com.raffaeleflorio.oogent.agent.rag;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.LLM;
import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.PromptTemplate;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.agent.TextResponse;
import com.raffaeleflorio.oogent.agent.TextSource;

import java.util.Map;

public final class RAGAgent implements Agent {

    private final LLM llm;
    private final PromptTemplate promptTemplate;
    private final Agent contextAgent;
    private final Text contextSourceId;

    public RAGAgent(
            final LLM llm,
            final PromptTemplate promptTemplate,
            final Agent contextAgent
    ) {
        this(
                llm,
                promptTemplate,
                contextAgent,
                new PlainText("context")
        );
    }

    public RAGAgent(
            final LLM llm,
            final PromptTemplate promptTemplate,
            final Agent contextAgent,
            final Text contextSourceId
    ) {
        this.llm = llm;
        this.promptTemplate = promptTemplate;
        this.contextAgent = contextAgent;
        this.contextSourceId = contextSourceId;
    }

    @Override
    public Response response(final Text input) {
        var context = this.contextAgent.response(input);
        var completion = this.llm.completion(
                this.promptTemplate.prompt(
                        Map.of(
                                new PlainText("input"), input,
                                new PlainText("context"), context
                        )
                )
        );
        return new TextResponse(
                completion.text(),
                context.sources().with(new TextSource(this.contextSourceId, context)),
                context.tokenUsage().sum(completion.tokenUsage())
        );
    }
}
