package com.raffaeleflorio.oogent.text.file;

import com.raffaeleflorio.oogent.PlainText;
import com.raffaeleflorio.oogent.Text;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.nio.file.Path;

public final class PDF implements Text {

    private final Path path;

    public PDF(final Text path) {
        this(path.asString());
    }

    public PDF(final String path) {
        this(Path.of(path));
    }

    public PDF(final Path path) {
        this.path = path;
    }

    @Override
    public String asString() {
        try (var doc = Loader.loadPDF(this.path.toFile())) {
            return new PDFTextStripper().getText(doc);
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
        return new PlainText(this.asString()).contains(text);
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
        return this.asString().startsWith(prefix.asString());
    }

    @Override
    public Boolean empty() {
        return this.asString().isEmpty();
    }
}
