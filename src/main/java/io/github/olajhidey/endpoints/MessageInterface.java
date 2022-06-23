package io.github.olajhidey.endpoints;

import io.github.olajhidey.model.SuccessResponse;
import io.github.olajhidey.model.message.Message;
import io.github.olajhidey.model.message.Messages;
import io.github.olajhidey.model.message.NewMessageResponse;

public interface MessageInterface {

    Messages getMessages();

    Messages getMessages(String dateSent, String from, String to, String status, String pageSize);

    NewMessageResponse create(String to, String from, String body);

    NewMessageResponse create(String to, String from, String body, String mediaUrl, String statusCallback, String validityPeriod);

    Message get(String sid);

    NewMessageResponse update(String sid, String body);

    SuccessResponse delete(String sid);
}
