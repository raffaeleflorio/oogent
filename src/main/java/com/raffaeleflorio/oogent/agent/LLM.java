package com.raffaeleflorio.oogent.agent;

import com.raffaeleflorio.oogent.Text;

public interface LLM {

    Text completion(Text text);
}
