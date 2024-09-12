package com.raffaeleflorio.oogent.tool;

import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.Tool;

public final class DelimitedSignature implements Tool.Signature {

    private final Text id;
    private final Text description;
    private final Text delimiter;

    public DelimitedSignature(final Text id, final Text description) {
        this(id, description, new PlainText(": "));
    }

    public DelimitedSignature(final Text id, final Text description, final Text delimiter) {
        this.id = id;
        this.description = description;
        this.delimiter = delimiter;
    }

    @Override
    public Text id() {
        return this.id;
    }

    @Override
    public Text description() {
        return this.description;
    }

    @Override
    public Text asText() {
        return this.id.then(this.delimiter).then(this.description);
    }
}
