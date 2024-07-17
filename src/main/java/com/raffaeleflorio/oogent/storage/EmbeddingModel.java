package com.raffaeleflorio.oogent.storage;

import com.raffaeleflorio.oogent.Text;

public interface EmbeddingModel {

    Embedding embedding(Text text);
}
