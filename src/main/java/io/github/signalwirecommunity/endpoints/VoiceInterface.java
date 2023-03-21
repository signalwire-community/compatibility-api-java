package io.github.signalwirecommunity.endpoints;

import io.github.signalwirecommunity.exceptions.SignalWireException;
import io.github.signalwirecommunity.model.SuccessResponse;
import io.github.signalwirecommunity.model.call.Call;
import io.github.signalwirecommunity.model.call.CallFilter;
import io.github.signalwirecommunity.model.call.CallResponse;
import io.github.signalwirecommunity.model.call.VoiceBuilder;
import io.github.signalwirecommunity.model.message.Messages;

import java.util.Map;

public interface VoiceInterface {

    CallResponse calls();

    CallResponse calls(CallFilter filter);

    Call create(VoiceBuilder builder) throws SignalWireException;

    Call get(String sid);

    Call update(String sid, VoiceBuilder builder) throws SignalWireException;

    SuccessResponse delete(String sid);
}
