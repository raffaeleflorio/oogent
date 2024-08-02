package com.raffaeleflorio.oogent.agent;

import com.raffaeleflorio.oogent.LLM;
import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Sources;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.TokenUsage;

public final class TextResponse implements Response {

    private final LLM.Completion completion;
    private final Sources sources;

    public TextResponse(final String text) {
        this(new PlainText(text));
    }

    public TextResponse(final Text text) {
        this(text, new EmptySources());
    }

    public TextResponse(final Text text, final Sources sources) {
        this(
                text,
                sources,
                new ZeroTokenUsage()
        );
    }

    public TextResponse(final Text text, final Sources sources, TokenUsage tokenUsage) {
        this(
                new LLM.Completion() {
                    @Override
                    public Text text() {
                        return text;
                    }

                    @Override
                    public TokenUsage tokenUsage() {
                        return tokenUsage;
                    }
                },
                sources
        );
    }

    public TextResponse(final LLM.Completion completion) {
        this(
                completion,
                new EmptySources()
        );
    }

    public TextResponse(final LLM.Completion completion, final Sources sources) {
        this.completion = completion;
        this.sources = sources;
    }

    @Override
    public Sources sources() {
        return this.sources;
    }

    @Override
    public TokenUsage tokenUsage() {
        return this.completion.tokenUsage();
    }

    @Override
    public Text then(final Text text) {
        return this.completion.text().then(text);
    }

    @Override
    public Boolean contains(final Text text) {
        return this.completion.text().contains(text);
    }

    @Override
    public Text afterFirst(final Text text) {
        return this.completion.text().afterFirst(text);
    }

    @Override
    public Text afterLast(final Text text) {
        return this.completion.text().afterLast(text);
    }

    @Override
    public Text beforeFirst(final Text text) {
        return this.completion.text().beforeFirst(text);
    }

    @Override
    public Text beforeLast(final Text text) {
        return this.completion.text().beforeLast(text);
    }

    @Override
    public Boolean startsWith(final Text prefix) {
        return this.completion.text().startsWith(prefix);
    }

    @Override
    public Boolean blank() {
        return this.completion.text().blank();
    }

    @Override
    public Integer size() {
        return this.completion.text().size();
    }

    @Override
    public Text sub(final Integer start, final Integer endExcluded) {
        return this.completion.text().sub(start, endExcluded);
    }

    @Override
    public String asString() {
        return this.completion.text().asString();
    }
}
