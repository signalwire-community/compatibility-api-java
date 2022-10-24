package io.github.signalwirecommunity.endpoints;

import io.github.signalwirecommunity.model.SuccessResponse;
import io.github.signalwirecommunity.model.message.*;

import java.util.HashMap;

public interface MessageInterface {

    Messages getMessages();
    NewMessageResponse create(String to, String from, String body);

    NewMessageResponse create(MessageRequest request);

    Message get(String sid);

    NewMessageResponse update(String sid, String body);

    SuccessResponse delete(String sid);

    Messages getMessages(MessageFilter filter);
}
