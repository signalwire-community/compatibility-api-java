package io.github.signalwirecommunity.endpoints;

import io.github.signalwirecommunity.model.SuccessResponse;
import io.github.signalwirecommunity.model.message.Message;
import io.github.signalwirecommunity.model.message.Messages;
import io.github.signalwirecommunity.model.message.NewMessageResponse;

public interface MessageInterface {

    Messages getMessages();

    Messages getMessages(String dateSent, String from, String to, String status, String pageSize);

    NewMessageResponse create(String to, String from, String body);

    NewMessageResponse create(String to, String from, String body, String mediaUrl, String statusCallback, String validityPeriod);

    Message get(String sid);

    NewMessageResponse update(String sid, String body);

    SuccessResponse delete(String sid);
}
