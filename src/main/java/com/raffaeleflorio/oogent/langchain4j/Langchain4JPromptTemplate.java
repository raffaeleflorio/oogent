package com.raffaeleflorio.oogent.langchain4j;

import com.raffaeleflorio.oogent.PromptTemplate;

import java.util.Map;
import java.util.stream.Collectors;

public final class Langchain4JPromptTemplate implements PromptTemplate {

    private final dev.langchain4j.model.input.PromptTemplate promptTemplate;

    public Langchain4JPromptTemplate(final String promptTemplate) {
        this(dev.langchain4j.model.input.PromptTemplate.from(promptTemplate));
    }

    public Langchain4JPromptTemplate(final dev.langchain4j.model.input.PromptTemplate promptTemplate) {
        this.promptTemplate = promptTemplate;
    }

    @Override
    public String prompt(final Map<String, String> variables) {
        return this.promptTemplate
                .apply(variables
                        .entrySet()
                        .stream()
                        .collect(Collectors.<Map.Entry<String, String>, String, Object>toMap(Map.Entry::getKey, Map.Entry::getValue))
                )
                .text();
    }
}
