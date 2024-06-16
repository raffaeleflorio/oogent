package com.raffaeleflorio.oogent.rag;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.LLM;
import com.raffaeleflorio.oogent.PromptTemplate;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Storage;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.simple.SimpleResponse;

import java.util.Map;
import java.util.stream.Collectors;

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
        var message = this.promptTemplate.prompt(Map.of("context", this.context(text), "text", text.text()));
        return new SimpleResponse(this.llm.completion(message));
    }

    private String context(final Text text) {
        return this.storage.output(text)
                .stream()
                .map(Text::text)
                .collect(Collectors.joining("\n"));
    }
}
