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

public final class RAGAgent implements Agent {

    private final Storage storage;
    private final LLM llm;
    private final PromptTemplate promptTemplate;

    public RAGAgent(final Storage storage, final LLM llm, final PromptTemplate promptTemplate) {
        this.storage = storage;
        this.llm = llm;
        this.promptTemplate = promptTemplate;
    }

    @Override
    public Response response(final Text text) {
        var message = this.promptTemplate.prompt(this.variables(text));
        return new TextResponse(this.llm.completion(message));
    }

    private Map<Text, Text> variables(final Text text) {
        return Map.of(
                new PlainText("text"), text,
                new PlainText("context"), this.storage.output(text).listed()
        );
    }
}
