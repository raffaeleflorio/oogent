package com.raffaeleflorio.oogent.regex;

import com.raffaeleflorio.oogent.Agent;
import com.raffaeleflorio.oogent.Response;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.simple.SimpleResponse;

import java.util.regex.Pattern;

public final class RegexAgent implements Agent {

    private final Pattern regex;

    public RegexAgent(final Pattern regex) {
        this.regex = regex;
    }

    @Override
    public Response response(final Text text) {
        return new SimpleResponse(this.regex.split(text.text())[1]);
    }
}
