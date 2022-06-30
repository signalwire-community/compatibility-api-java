package io.github.signalwirecommunity.endpoints;

import io.github.signalwirecommunity.model.SuccessResponse;
import io.github.signalwirecommunity.model.call.Call;
import io.github.signalwirecommunity.model.call.CallResponse;
import io.github.signalwirecommunity.model.message.Messages;

public interface VoiceInterface {

    CallResponse calls();

    Messages calls(
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

    Call create(String from,
                String to,
                String url,
                Boolean record,
                String statusCallback);

    Call get(String sid);

    Call update(String sid, String url, String fallbackUrl, String status, String statusCallBack);

    SuccessResponse delete(String sid);
}
