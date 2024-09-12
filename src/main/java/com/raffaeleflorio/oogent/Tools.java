package com.raffaeleflorio.oogent;

public interface Tools extends Iterable<Tool> {

    Boolean contains(Text id);

    Tool tool(Text id);

    Text listed(Text prefix);
}
