package io.github.signalwirecommunity.model.message;

import io.github.signalwirecommunity.endpoints.CreateMessageBody;
import java.util.HashMap;


public class MessageRequest {

    public HashMap<String, Object> response;

    public MessageRequest(Builder builder) {
        response = builder.data;
    }

    public HashMap<String, Object> getResponse() {
        return response;
    }

    public static class Builder implements CreateMessageBody {

        private HashMap<String, Object> data;

        public Builder() {
            data = new HashMap<>();
        }


        @Override
        public Builder to(String phone) {
            data.put("To", phone);
            return this;
        }

        @Override
        public Builder from(String phone) {
            data.put("From", phone);
            return this;
        }

        @Override
        public Builder body(String body) {
            data.put("Body", body);
            return this;
        }

        @Override
        public Builder mediaUrl(String url) {
            data.put("MediaUrl", url);
            return this;
        }

        @Override
        public Builder applicationSid(String applicationSid) {
            data.put("ApplicationSid", applicationSid);
            return this;
        }

        @Override
        public Builder maxPrice(String maxPrice) {
            data.put("MaxPrice", maxPrice);
            return this;
        }

        @Override
        public Builder statusCallBack(String statusCallBack) {
            data.put("StatusCallBack", statusCallBack);
            return this;
        }

        @Override
        public Builder validityPeriod(int validityPeriod) {
            data.put("ValidityPeriod", String.valueOf(validityPeriod));
            return this;
        }

        @Override
        public Builder messagingServiceSid(String messageServiceSid) {
            data.put("MessagingServiceSid", messageServiceSid);
            return this;
        }

        public MessageRequest build() {
            return new MessageRequest(this);
        }

    }

}
