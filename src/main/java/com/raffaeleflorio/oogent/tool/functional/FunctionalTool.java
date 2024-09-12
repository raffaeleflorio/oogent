package com.raffaeleflorio.oogent.tool.functional;

import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.Tool;
import com.raffaeleflorio.oogent.tool.DelimitedSignature;

import java.util.function.Function;

public final class FunctionalTool implements Tool {

    private final Signature signature;
    private final Function<? super Text, ? extends Text> fn;

    public FunctionalTool(final String id, final String description, final Function<? super Text, ? extends Text> fn) {
        this(
                new PlainText(id),
                new PlainText(description),
                fn
        );
    }

    public FunctionalTool(final Text id, final Text description, final Function<? super Text, ? extends Text> fn) {
        this(
                new DelimitedSignature(id,description),
                fn
        );
    }

    public FunctionalTool(final Signature signature, final Function<? super Text, ? extends Text> fn) {
        this.signature = signature;
        this.fn = fn;
    }

    @Override
    public Signature signature() {
        return this.signature;
    }

    @Override
    public Text result(final Text args) {
        return this.fn.apply(args);
    }
}
