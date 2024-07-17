package com.raffaeleflorio.oogent.storage;

import com.raffaeleflorio.oogent.Text;

public interface Document {

    Text id();

    Double score();

    Text text();
}
