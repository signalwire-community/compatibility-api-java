package io.github.signalwirecommunity.endpoints;

import io.github.signalwirecommunity.model.SuccessResponse;
import io.github.signalwirecommunity.model.call.Call;
import io.github.signalwirecommunity.model.call.CallResponse;
import io.github.signalwirecommunity.model.message.Messages;

import java.util.Map;

public interface VoiceInterface {

    CallResponse calls();

    CallResponse calls(
            String endTime,
            String endTimeBefore,
            String endTimeAfter,
            String from,
            String parentCallSid,
            String startTime,
            String startTimeBefore,
            String startTimeAfter,
            String status,
            String to);

    Call create(Map<String, Object> callInfo);

    Call get(String sid);

    Call update(String sid, Map<String, Object> callInfo);

    SuccessResponse delete(String sid);
}
