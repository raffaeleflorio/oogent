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
    public Text then(final Text text) {
        return this.pdfText().then(text);
    }

    private Text pdfText() {
        return new PlainText(this.asString());
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
    public Boolean contains(final Text text) {
        return this.pdfText().contains(text);
    }

    @Override
    public Text afterFirst(final Text text) {
        return this.pdfText().afterFirst(text);
    }

    @Override
    public Text afterLast(final Text text) {
        return this.pdfText().afterLast(text);
    }

    @Override
    public Text beforeFirst(final Text text) {
        return this.pdfText().beforeFirst(text);
    }

    @Override
    public Text beforeLast(final Text text) {
        return this.pdfText().beforeLast(text);
    }

    @Override
    public Boolean startsWith(final Text prefix) {
        return this.pdfText().startsWith(prefix);
    }

    @Override
    public Boolean blank() {
        return this.pdfText().blank();
    }

    @Override
    public Integer size() {
        return this.pdfText().size();
    }

    @Override
    public Text sub(final Integer start, final Integer endExcluded) {
        return this.pdfText().sub(start, endExcluded);
    }
}
