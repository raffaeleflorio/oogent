package com.raffaeleflorio.oogent.agent.conversational;

import com.raffaeleflorio.oogent.Text;

public interface Message extends Text {

    Boolean ai();

    Boolean human();
}
