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
    public Boolean contains(final Text subText) {
        return this.pdfText().contains(subText);
    }

    @Override
    public Text afterFirst(final Text subText) {
        return this.pdfText().afterFirst(subText);
    }

    @Override
    public Text afterLast(final Text subText) {
        return this.pdfText().afterLast(subText);
    }

    @Override
    public Text beforeFirst(final Text subText) {
        return this.pdfText().beforeFirst(subText);
    }

    @Override
    public Text beforeLast(final Text subText) {
        return this.pdfText().beforeLast(subText);
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
