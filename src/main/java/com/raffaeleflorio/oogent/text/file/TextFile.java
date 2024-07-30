package com.raffaeleflorio.oogent.text.file;

import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.Text;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class TextFile implements Text {

    private final Path path;
    private final Charset charset;

    public TextFile(final Text path) {
        this(path.asString());
    }

    public TextFile(final String path) {
        this(Path.of(path));
    }

    public TextFile(final Path path) {
        this(path, StandardCharsets.UTF_8);
    }

    public TextFile(final Text path, final Charset charset) {
        this(path.asString(), charset);
    }

    public TextFile(final String path, final Charset charset) {
        this(Path.of(path), charset);
    }

    public TextFile(final Path path, final Charset charset) {
        this.path = path;
        this.charset = charset;
    }

    @Override
    public String asString() {
        try {
            return Files.readString(this.path, this.charset);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Text then(final Text text) {
        return new PlainText(this.asString()).then(text);
    }

    @Override
    public Boolean contains(final Text text) {
        return this.asString().contains(text.asString());
    }

    @Override
    public Text afterFirst(final Text text) {
        return new PlainText(this.asString()).afterFirst(text);
    }

    @Override
    public Text afterLast(final Text text) {
        return new PlainText(this.asString()).afterLast(text);
    }

    @Override
    public Text beforeFirst(final Text text) {
        return new PlainText(this.asString()).beforeFirst(text);
    }

    @Override
    public Text beforeLast(final Text text) {
        return new PlainText(this.asString()).beforeLast(text);
    }

    @Override
    public Boolean startsWith(final Text prefix) {
        return this.startsWith(prefix.asString());
    }

    private Boolean startsWith(final String prefix) {
        try {
            return this.read(prefix.length()).startsWith(prefix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String read(final Integer length) throws IOException {
        var cb = CharBuffer.allocate(length);
        try (var br = Files.newBufferedReader(this.path, this.charset)) {
            br.read(cb);
        }
        return cb.flip().toString();
    }

    @Override
    public Boolean empty() {
        try {
            return Files.size(this.path) == 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
