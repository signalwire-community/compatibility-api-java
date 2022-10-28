package io.github.signalwirecommunity.endpoints;

import io.github.signalwirecommunity.model.message.MessageRequest;

public interface CreateMessageBody {

    MessageRequest.Builder to(String phone);

    MessageRequest.Builder from(String phone);

    MessageRequest.Builder body(String body);

    MessageRequest.Builder mediaUrl(String url);

    MessageRequest.Builder applicationSid(String applicationSid);

    MessageRequest.Builder maxPrice(String maxPrice);

    MessageRequest.Builder statusCallBack(String statusCallBack);

    MessageRequest.Builder validityPeriod(int validityPeriod);

    MessageRequest.Builder messagingServiceSid(String messageServiceSid);
}
