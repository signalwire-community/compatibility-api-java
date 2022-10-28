package io.github.signalwirecommunity.model.call;

import io.github.signalwirecommunity.endpoints.CallFilterInterface;

import java.util.HashMap;

public class CallFilter {

    public HashMap<String, Object> response;

    public CallFilter(Builder builder){
        response = builder.response;
    }

    public static class Builder implements CallFilterInterface {
        private HashMap<String, Object> response;

        public Builder(){
            this.response = new HashMap<>();
        }

        @Override
        public Builder endTime(String endTime) {
            response.put("EndTime", endTime);
            return this;
        }

        @Override
        public Builder endTimeBefore(String endTime) {
            response.put("EndTime<", endTime);
            return this;
        }

        @Override
        public Builder endTimeAfter(String endTime) {
            response.put("EndTime>", endTime);
            return this;
        }

        @Override
        public Builder from(String phone) {
            response.put("From", phone);
            return this;
        }

        @Override
        public Builder parentCallSid(String sid) {
            response.put("ParentCallSid", sid);
            return this;
        }

        @Override
        public Builder startTime(String startTime) {
            response.put("StartTime", startTime);
            return this;
        }

        @Override
        public Builder startTimeBefore(String startTime) {
            response.put("StartTime<", startTime);
            return this;
        }

        @Override
        public Builder startTimeAfter(String startTime) {
            response.put("StartTime>", startTime);
            return this;
        }

        @Override
        public Builder status(String status) {
            response.put("Status", status);
            return this;
        }

        @Override
        public Builder to(String phone) {
            response.put("To", phone);
            return this;
        }

        public Builder pageSize(int pageSize){
            response.put("PageSize", String.valueOf(pageSize));
            return this;
        }

        public CallFilter build(){
            return new CallFilter(this);
        }
    }

}
