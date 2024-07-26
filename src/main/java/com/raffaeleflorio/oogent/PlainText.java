package com.raffaeleflorio.oogent;

public final class PlainText implements Text {

    private final String text;

    public PlainText(final String text) {
        this.text = text;
    }

    @Override
    public String asString() {
        return this.text;
    }

    @Override
    public Text then(final Text text) {
        return new PlainText(this.text.concat(text.asString()));
    }

    @Override
    public Boolean contains(final Text text) {
        return this.text.contains(text.asString());
    }

    @Override
    public Text afterFirst(final Text text) {
        var offset = text.asString().length();
        var begin = this.text.indexOf(text.asString()) + offset;
        if (begin < offset) {
            throw new IllegalArgumentException(
                    "I'm unable to build the text after the first occurrence of the given text. It's missing"
            );
        }
        return this.substring(begin, this.text.length());
    }

    private Text substring(final Integer begin, final Integer end) {
        return new PlainText(this.text.substring(begin, end));
    }

    @Override
    public Text afterLast(final Text text) {
        var offset = text.asString().length();
        var begin = this.text.lastIndexOf(text.asString()) + offset;
        if (begin < offset) {
            throw new IllegalArgumentException(
                    "I'm unable to build the text after the last occurrence of the given text. It's missing"
            );
        }
        return this.substring(begin, this.text.length());
    }

    @Override
    public Text beforeFirst(final Text text) {
        var end = this.text.indexOf(text.asString());
        if (end == -1) {
            throw new IllegalArgumentException(
                    "I'm unable to build the text before the first occurrence of the given text. It's missing"
            );
        }
        return this.substring(0, end);
    }

    @Override
    public Text beforeLast(final Text text) {
        var end = this.text.lastIndexOf(text.asString());
        if (end == -1) {
            throw new IllegalArgumentException(
                    "I'm unable to build the text before the last occurrence of the given text. It's missing"
            );
        }
        return this.substring(0, end);
    }

    @Override
    public Boolean startsWith(final Text prefix) {
        return this.text.startsWith(prefix.asString());
    }

    @Override
    public Boolean empty() {
        return this.text.isBlank();
    }
}
