package com.raffaeleflorio.oogent.agent.action;

import com.raffaeleflorio.oogent.Action;
import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.LLM;
import com.raffaeleflorio.oogent.PromptTemplate;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.text.Listed;
import com.raffaeleflorio.oogent.text.PlainText;
import com.raffaeleflorio.oogent.text.response.TextResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class ReActAgent implements Agent {

    private final LLM llm;
    private final PromptTemplate promptTemplate;
    private final Function<Action, Text> actionFn;
    private final Text endResultKeyword;
    private final Text actionIdKeyword;
    private final Text actionInputKeyword;
    private final Text actionOutputKeyword;
    private final Text thoughtKeyword;
    private final Map<String, Action> actions;

    public ReActAgent(
            final LLM llm,
            final PromptTemplate promptTemplate,
            final Function<Action, Text> actionFn,
            final Text endResultKeyword,
            final Text actionIdKeyword,
            final Text actionInputKeyword,
            final Text actionOutputKeyword,
            final Text thoughtKeyword,
            final Action... actions
    ) {
        this(
                llm,
                promptTemplate,
                actionFn,
                endResultKeyword,
                actionIdKeyword,
                actionInputKeyword,
                actionOutputKeyword,
                thoughtKeyword,
                Arrays.asList(actions)
        );
    }

    public ReActAgent(
            final LLM llm,
            final PromptTemplate promptTemplate,
            final Function<Action, Text> actionFn,
            final Text endResultKeyword,
            final Text actionIdKeyword,
            final Text actionInputKeyword,
            final Text actionOutputKeyword,
            final Text thoughtKeyword,
            final List<Action> actions
    ) {
        this(
                llm,
                promptTemplate,
                actionFn,
                endResultKeyword,
                actionIdKeyword,
                actionInputKeyword,
                actionOutputKeyword,
                thoughtKeyword,
                actions
                        .stream()
                        .collect(Collectors.toMap(action -> action.id().asString(), Function.identity()))
        );
    }

    ReActAgent(
            final LLM llm,
            final PromptTemplate promptTemplate,
            final Function<Action, Text> actionFn,
            final Text endResultKeyword,
            final Text actionIdKeyword,
            final Text actionInputKeyword,
            final Text actionOutputKeyword,
            final Text thoughtKeyword,
            final Map<String, Action> actions
    ) {
        this.llm = llm;
        this.promptTemplate = promptTemplate;
        this.actionFn = actionFn;
        this.endResultKeyword = endResultKeyword;
        this.actionIdKeyword = actionIdKeyword;
        this.actionInputKeyword = actionInputKeyword;
        this.actionOutputKeyword = actionOutputKeyword;
        this.thoughtKeyword = thoughtKeyword;
        this.actions = actions;
    }

    @Override
    public Response response(final Text text) {
        var previousPrompt = this.prompt(text);
        var result = this.llm.completion(previousPrompt);
        while (!result.contains(this.endResultKeyword)) {
            var nextPrompt = previousPrompt
                    .then(result)
                    .then(this.actionOutputKeyword)
                    .then(this.actionOutput(result))
                    .then(new PlainText("\n"))
                    .then(this.thoughtKeyword);
            result = this.llm.completion(nextPrompt);
            previousPrompt = nextPrompt;
        }
        return new TextResponse(result.afterLast(this.endResultKeyword));
    }

    private Text prompt(final Text text) {
        return this.promptTemplate.prompt(
                Map.of(
                        new PlainText("actions"), this.actions(),
                        new PlainText("text"), text
                )
        );
    }

    private Text actions() {
        return new Listed(
                this.actions
                        .values()
                        .stream()
                        .map(this.actionFn)
                        .toList()
        );
    }

    private Text actionOutput(final Text result) {
        var actionId = result.afterLast(this.actionIdKeyword).beforeFirst(this.actionInputKeyword);
        var actionInput = result.afterLast(this.actionInputKeyword);
        return this.actions.get(actionId.asString().trim()).response(actionInput);
    }
}
