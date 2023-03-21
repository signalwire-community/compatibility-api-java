package io.github.signalwirecommunity.endpoints;

import io.github.signalwirecommunity.exceptions.SignalWireException;
import io.github.signalwirecommunity.model.SuccessResponse;
import io.github.signalwirecommunity.model.message.*;

import java.util.HashMap;

public interface MessageInterface {

    Messages getMessages();
    NewMessageResponse create(String to, String from, String body) throws SignalWireException;

    NewMessageResponse create(MessageRequest request) throws SignalWireException;

    Message get(String sid);

    NewMessageResponse update(String sid, String body) throws SignalWireException;

    SuccessResponse delete(String sid);

    Messages getMessages(MessageFilter filter);
}
