package com.raffaeleflorio.oogent.langchain4j;

import com.raffaeleflorio.oogent.PromptTemplate;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.simple.SimpleText;

import java.util.Map;
import java.util.stream.Collectors;

public final class Langchain4JPromptTemplate implements PromptTemplate {

    private final dev.langchain4j.model.input.PromptTemplate promptTemplate;

    public Langchain4JPromptTemplate(final String promptTemplate) {
        this(new SimpleText(promptTemplate));
    }

    public Langchain4JPromptTemplate(final Text promptTemplate) {
        this(dev.langchain4j.model.input.PromptTemplate.from(promptTemplate.asString()));
    }

    public Langchain4JPromptTemplate(final dev.langchain4j.model.input.PromptTemplate promptTemplate) {
        this.promptTemplate = promptTemplate;
    }

    @Override
    public Text prompt(final Map<Text, Text> variables) {
        return new SimpleText(
                this.promptTemplate.apply(
                        variables
                                .entrySet()
                                .stream()
                                .map(entry -> Map.entry(entry.getKey().asString(), entry.getValue().asString()))
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                ).text()
        );
    }

    @Override
    public Text prompt(final Text text) {
        return this.prompt(Map.of(new SimpleText("it"), text));
    }
}
