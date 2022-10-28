package io.github.signalwirecommunity.model.message;

import io.github.signalwirecommunity.endpoints.MessageFilterInterface;
import io.github.signalwirecommunity.enums.MessageStatus;

import java.util.HashMap;

public class MessageFilter {

    private final HashMap<String, Object> data;

    public HashMap<String, Object> getData() {
        return data;
    }

    public MessageFilter(Builder builder){
        data = builder.data;
    }

    public static class Builder implements MessageFilterInterface {

        private HashMap<String, Object> data;

        public Builder() {
            data = new HashMap<>();
        }

        @Override
        public Builder dateSent(String year, String month, String day, String range) {
            String dateSent = String.format("%s-%s-%s", year, month, day);

            if (range != null || !range.isEmpty()){
                String key = "DateSent"+range;
                data.put(key, dateSent);
            }
            return this;
        }

        @Override
        public Builder from(String from) {
            data.put("From", from);
            return this;
        }

        @Override
        public Builder to(String to) {
            data.put("To", to);
            return this;
        }

        @Override
        public Builder status(MessageStatus messageStatus) {
            data.put("Status", messageStatus.name());
            return this;
        }

        @Override
        public Builder pageSize(int size) {
            data.put("PageSize", String.valueOf(size));
            return this;
        }

        public MessageFilter build(){
            return new MessageFilter(this);
        }
    }

}
