package com.raffaeleflorio.oogent.agent;

import com.raffaeleflorio.oogent.LLM;
import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Sources;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.TokenUsage;

public final class TextResponse implements Response {

    private final Text text;
    private final Sources sources;
    private final TokenUsage tokenUsage;

    public TextResponse(final String text) {
        this(new PlainText(text));
    }

    public TextResponse(final LLM.Completion completion) {
        this(completion.text());
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

    public TextResponse(final LLM.Completion completion, final Sources sources) {
        this(
                completion.text(),
                sources,
                completion.tokenUsage()
        );
    }

    public TextResponse(final Text text, final Sources sources, TokenUsage tokenUsage) {
        this.text = text;
        this.sources = sources;
        this.tokenUsage = tokenUsage;
    }

    @Override
    public Sources sources() {
        return this.sources;
    }

    @Override
    public TokenUsage tokenUsage() {
        return this.tokenUsage;
    }

    @Override
    public Text then(final Text text) {
        return this.text.then(text);
    }

    @Override
    public Boolean contains(final Text subText) {
        return this.text.contains(subText);
    }

    @Override
    public Text afterFirst(final Text subText) {
        return this.text.afterFirst(subText);
    }

    @Override
    public Text afterLast(final Text subText) {
        return this.text.afterLast(subText);
    }

    @Override
    public Text beforeFirst(final Text subText) {
        return this.text.beforeFirst(subText);
    }

    @Override
    public Text beforeLast(final Text subText) {
        return this.text.beforeLast(subText);
    }

    @Override
    public Boolean startsWith(final Text prefix) {
        return this.text.startsWith(prefix);
    }

    @Override
    public Boolean blank() {
        return this.text.blank();
    }

    @Override
    public Integer size() {
        return this.text.size();
    }

    @Override
    public Text sub(final Integer start, final Integer endExcluded) {
        return this.text.sub(start, endExcluded);
    }

    @Override
    public String asString() {
        return this.text.asString();
    }
}
