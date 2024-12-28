package com.raffaeleflorio.oogent;

public final class PlainText implements Text {

    private final String text;

    public PlainText(final String text) {
        this.text = text;
    }

    @Override
    public Text then(final Text text) {
        return new PlainText(this.text.concat(text.asString()));
    }

    @Override
    public Boolean contains(final Text subText) {
        return this.text.contains(subText.asString());
    }

    @Override
    public Text afterFirst(final Text subText) {
        var offset = subText.asString().length();
        var begin = this.text.indexOf(subText.asString()) + offset;
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
    public Text afterLast(final Text subText) {
        var offset = subText.asString().length();
        var begin = this.text.lastIndexOf(subText.asString()) + offset;
        if (begin < offset) {
            throw new IllegalArgumentException(
                    "I'm unable to build the text after the last occurrence of the given text. It's missing"
            );
        }
        return this.substring(begin, this.text.length());
    }

    @Override
    public Text beforeFirst(final Text subText) {
        var end = this.text.indexOf(subText.asString());
        if (end == -1) {
            throw new IllegalArgumentException(
                    "I'm unable to build the text before the first occurrence of the given text. It's missing"
            );
        }
        return this.substring(0, end);
    }

    @Override
    public Text beforeLast(final Text subText) {
        var end = this.text.lastIndexOf(subText.asString());
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
    public Boolean blank() {
        return this.text.isBlank();
    }

    @Override
    public Integer size() {
        return this.text.codePointCount(0, this.text.length());
    }

    @Override
    public Text sub(final Integer start, final Integer endExcluded) {
        var beginIndex = this.text.offsetByCodePoints(0, start);
        var endIndex = this.text.offsetByCodePoints(0, endExcluded);
        return new PlainText(this.text.substring(beginIndex, endIndex));
    }

    @Override
    public String asString() {
        return this.text;
    }
}
