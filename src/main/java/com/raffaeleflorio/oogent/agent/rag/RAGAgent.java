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

    private final Agent contextAgent;
    private final LLM llm;
    private final PromptTemplate promptTemplate;
    private final PlainText contextSourceId;

    public RAGAgent(
            final Agent contextAgent,
            final LLM llm,
            final PromptTemplate promptTemplate
    ) {
        this(
                contextAgent,
                llm,
                promptTemplate,
                new PlainText("context")
        );
    }

    public RAGAgent(
            final Agent contextAgent,
            final LLM llm,
            final PromptTemplate promptTemplate,
            final PlainText contextSourceId
    ) {
        this.contextAgent = contextAgent;
        this.llm = llm;
        this.promptTemplate = promptTemplate;
        this.contextSourceId = contextSourceId;
    }

    @Override
    public Response response(final Text text) {
        var context = this.contextAgent.response(text);
        var completion = this.llm.completion(
                this.promptTemplate.prompt(
                        Map.of(
                                new PlainText("text"), text,
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
