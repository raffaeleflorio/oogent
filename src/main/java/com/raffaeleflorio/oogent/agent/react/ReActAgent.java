package com.raffaeleflorio.oogent.agent.react;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.LLM;
import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.PromptTemplate;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.TokenUsage;
import com.raffaeleflorio.oogent.Tool;
import com.raffaeleflorio.oogent.Tools;
import com.raffaeleflorio.oogent.agent.EmptySources;
import com.raffaeleflorio.oogent.agent.TextResponse;

import java.util.Map;

public final class ReActAgent implements Agent {

    private final Tools tools;
    private final LLM llm;
    private final PromptTemplate promptTemplate;
    private final Text toolIdHeader;
    private final Text toolArgsHeader;
    private final Text toolOutputHeader;
    private final Text finalAnswerHeader;

    public ReActAgent(
            final Tools tools,
            final LLM llm,
            final PromptTemplate promptTemplate
    ) {
        this(
                tools,
                llm,
                promptTemplate,
                new PlainText("Tool: "),
                new PlainText("Args: "),
                new PlainText("Output: "),
                new PlainText("Answer: ")
        );
    }

    public ReActAgent(
            final Tools tools,
            final LLM llm,
            final PromptTemplate promptTemplate,
            final Text toolIdHeader,
            final Text toolArgsHeader,
            final Text toolOutputHeader,
            final Text finalAnswerHeader
    ) {
        this.tools = tools;
        this.llm = llm;
        this.promptTemplate = promptTemplate;
        this.toolIdHeader = toolIdHeader;
        this.toolArgsHeader = toolArgsHeader;
        this.toolOutputHeader = toolOutputHeader;
        this.finalAnswerHeader = finalAnswerHeader;
    }

    @Override
    public Response response(final Text input) {
        var prompt = this.prompt(input);
        var completion = this.llm.completion(prompt);
        return this.response(prompt, completion.text(), completion.tokenUsage());
    }

    private Text prompt(final Text input) {
        return this.promptTemplate.prompt(
                Map.of(
                        new PlainText("input"), input,
                        new PlainText("tools"), this.tools.listed(new PlainText("- "))
                )
        );
    }

    private Response response(final Text prompt, final Text completion, final TokenUsage tokenUsage) {
        if (this.done(completion)) {
            return new TextResponse(this.finalAnswer(completion), new EmptySources(), tokenUsage);
        }
        var nextPrompt = this.nextPrompt(prompt, completion);
        var nextCompletion = this.llm.completion(nextPrompt);
        return this.response(nextPrompt, nextCompletion.text(), tokenUsage.sum(nextCompletion.tokenUsage()));
    }

    private Boolean done(final Text completion) {
        return completion.contains(this.finalAnswerHeader);
    }

    private Text finalAnswer(final Text completion) {
        return completion.afterLast(this.finalAnswerHeader);
    }

    private Text nextPrompt(final Text prompt, final Text completion) {
        return prompt
                .then(completion)
                .then(this.toolOutputHeader)
                .then(this.tool(completion).result(this.args(completion)))
                .then(new PlainText("\n"));
    }

    private Tool tool(final Text completion) {
        var id = completion.afterLast(this.toolIdHeader).beforeFirst(new PlainText("\n"));
        return this.tools.tool(id);
    }

    private Text args(final Text completion) {
        return completion.afterLast(this.toolArgsHeader).beforeFirst(new PlainText("\n"));
    }
}
