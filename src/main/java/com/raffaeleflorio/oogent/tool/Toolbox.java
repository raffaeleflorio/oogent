package com.raffaeleflorio.oogent.tool;

import com.raffaeleflorio.oogent.Listed;
import com.raffaeleflorio.oogent.Text;
import com.raffaeleflorio.oogent.Tool;
import com.raffaeleflorio.oogent.Tools;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public final class Toolbox implements Tools {

    private final Map<String, Tool> tools;

    public Toolbox(final Tool... tools) {
        this(
                Arrays.asList(tools)
        );
    }

    public Toolbox(final Iterable<? extends Tool> tools) {
        this(
                StreamSupport.stream(tools.spliterator(), false)
                        .collect(Collectors.toUnmodifiableMap(tool -> tool.id().asString(), Function.identity()))
        );
    }

    Toolbox(final Map<String, Tool> tools) {
        this.tools = tools;
    }

    @Override
    public Boolean contains(final Text id) {
        return this.tools.containsKey(id.asString());
    }

    @Override
    public Tool tool(final Text id) {
        return this.tools.getOrDefault(
                id.asString(),
                new MissingTool(id)
        );
    }

    @Override
    public Text listed(final Text prefix) {
        return new Listed(
                this.tools.values().stream()
                        .map(Tool::signature)
                        .map(prefix::then)
                        .toList()
        );
    }

    @Override
    public Iterator<Tool> iterator() {
        return this.tools.values().iterator();
    }
}
