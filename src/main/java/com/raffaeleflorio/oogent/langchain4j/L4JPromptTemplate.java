package com.raffaeleflorio.oogent.langchain4j;

import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.PromptTemplate;
import com.raffaeleflorio.oogent.Text;

import java.util.Map;
import java.util.stream.Collectors;

public final class L4JPromptTemplate implements PromptTemplate {

    private final dev.langchain4j.model.input.PromptTemplate promptTemplate;

    public L4JPromptTemplate(final Text promptTemplate) {
        this(promptTemplate.asString());
    }

    public L4JPromptTemplate(final String promptTemplate) {
        this(dev.langchain4j.model.input.PromptTemplate.from(promptTemplate));
    }

    public L4JPromptTemplate(final dev.langchain4j.model.input.PromptTemplate promptTemplate) {
        this.promptTemplate = promptTemplate;
    }

    @Override
    public Text prompt(final Map<Text, Text> variables) {
        return new PlainText(
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
        return this.prompt(Map.of(new PlainText("it"), text));
    }
}
