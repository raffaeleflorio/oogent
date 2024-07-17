package com.raffaeleflorio.oogent.storage;

import java.util.List;

public interface Embedding {

    Integer dimension();

    List<Double> values();
}
