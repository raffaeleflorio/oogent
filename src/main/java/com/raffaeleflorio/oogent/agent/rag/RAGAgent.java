package com.raffaeleflorio.oogent.agent.rag;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.LLM;
import com.raffaeleflorio.oogent.PromptTemplate;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Storage;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.text.PlainText;
import com.raffaeleflorio.oogent.text.response.TextResponse;

import java.util.Map;
import java.util.function.BiFunction;

public final class RAGAgent implements Agent {

    private final Storage storage;
    private final LLM llm;
    private final PromptTemplate promptTemplate;
    private final BiFunction<Text, Text, Response> responseFn;

    public RAGAgent(final Storage storage, final LLM llm, final PromptTemplate promptTemplate) {
        this(storage, llm, promptTemplate, (text, context) -> new TextResponse(text));
    }

    public RAGAgent(
            final Storage storage,
            final LLM llm,
            final PromptTemplate promptTemplate,
            final BiFunction<Text, Text, Response> responseFn
    ) {
        this.storage = storage;
        this.llm = llm;
        this.promptTemplate = promptTemplate;
        this.responseFn = responseFn;
    }

    @Override
    public Response response(final Text text) {
        var context = this.storage.output(text).listed();
        var message = this.promptTemplate.prompt(this.variables(text, context));
        return this.responseFn.apply(this.llm.completion(message), context);
    }

    private Map<Text, Text> variables(final Text text, final Text context) {
        return Map.of(
                new PlainText("text"), text,
                new PlainText("context"), context
        );
    }
}
