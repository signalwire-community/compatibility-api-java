package io.github.signalwirecommunity.endpoints;

import io.github.signalwirecommunity.enums.MessageStatus;
import io.github.signalwirecommunity.model.message.MessageFilter;

public interface MessageFilterInterface {
    MessageFilter.Builder dateSent(String year, String month, String day, String range);
    MessageFilter.Builder from(String from);
    MessageFilter.Builder to(String to);
    MessageFilter.Builder status(MessageStatus messageStatus);
    MessageFilter.Builder pageSize(int size);
}
