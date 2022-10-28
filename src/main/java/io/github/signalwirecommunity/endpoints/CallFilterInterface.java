package io.github.signalwirecommunity.endpoints;

import io.github.signalwirecommunity.model.call.CallFilter;

public interface CallFilterInterface {

    CallFilter.Builder endTime(String endTime);

    CallFilter.Builder endTimeBefore(String endTime);

    CallFilter.Builder endTimeAfter(String endTime);

    CallFilter.Builder from(String phone);

    CallFilter.Builder parentCallSid(String sid);

    CallFilter.Builder startTime(String startTime);

    CallFilter.Builder startTimeBefore(String startTime);

    CallFilter.Builder startTimeAfter(String startTime);

    CallFilter.Builder status(String status);

    CallFilter.Builder to(String phone);

}
