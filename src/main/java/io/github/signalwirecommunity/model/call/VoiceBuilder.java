package io.github.signalwirecommunity.model.call;

import io.github.signalwirecommunity.enums.MachineDetection;
import io.github.signalwirecommunity.enums.StatusCallBack;

import java.util.HashMap;
import java.util.Map;

public class VoiceBuilder {

    private final Map<String, Object> response;

    public VoiceBuilder(Builder builder) {
        this.response = builder.response;
    }

    public Map<String, Object> getResponse() {
        return response;
    }

    public static class Builder {

        private Map<String, Object> response;

        public static HashMap<String, Object> parameters;

        public Builder() {
            parameters = new HashMap();
        }

        /**
         *
         * @param url Url to the action of the call
         * @return Builder
         */
        public Builder url(String url) {
            addItem("Url", url);
            return this;
        }

        /**
         * Application SID if you are making use of an application to make call
         * @param sid application SID to the created application
         * @return Builder
         */
        public Builder applicationSid(String sid) {
            addItem("ApplicationSid", sid);
            return this;
        }

        /**
         * The address that initiated the call. Can be either a E.164 formatted number (+xxxxxxxxxxx), or a SIP endpoint (sip:xxx@yyy.zzz).
         * @param phone A E.164 formatted number (+xxxxxxxxxxx), or a SIP endpoint (sip:xxx@yyy.zzz).
         * @return Builder
         */
        public Builder from(String phone) {
            addItem("From", phone);
            return this;
        }

        /**
         * The address that received the call. Can be either a E.164 formatted number (+xxxxxxxxxxx), or a SIP endpoint (sip:xxx@yyy.zzz).
         * @param phone A E.164 formatted number (+xxxxxxxxxxx), or a SIP endpoint (sip:xxx@yyy.zzz).
         * @return Builder
         */
        public Builder to(String phone) {
            addItem("To", phone);
            return this;
        }

        /**
         * The number, in E.164 format, or identifier of the caller.
         * @param callerId A E.164 format, or identifier of the caller.
         * @return Builder
         */
        public Builder callerId(String callerId) {
            addItem("CallerId", callerId);
            return this;
        }

        /**
         * Whether the request to FallbackUrl is a GET or a POST. Default is POST. If ApplicationSid is present, this parameter is ignored.
         * @param fallbackMethod Default is POST. If ApplicationSid is present, this parameter is ignored.
         * @return
         */
        public Builder fallbackMethod(String fallbackMethod) {
            addItem("FallbackMethod", fallbackMethod);
            return this;
        }

        /**
         * The URL SignalWire will request if errors occur when fetching the Url. If ApplicationSid is present, this parameter is ignored.
         * @param fallbackUrl provide a Url  for fallback
         * @return
         */
        public Builder fallbackUrl(String fallbackUrl) {
            addItem("FallbackUrl", fallbackUrl);
            return this;
        }

        /**
         * Whether a human or machine picked up the call. Possible values are Enable, DetectMessageEnd and none.
         * @param machineDetection Possible values are Enable, DetectMessageEnd and none.
         * @return Builder
         */
        public Builder machineDetection(MachineDetection machineDetection) {
            addItem("MachineDetection", machineDetection.name());
            return this;
        }

        /**
         * The time SignalWire will wait for machine detection before timing out. Default is 30 seconds.
         * @param machineDetectionTimeout Default is 30 seconds.
         * @return
         */
        public Builder machineDetectionTimeout(String machineDetectionTimeout) {
            addItem("MachineDetectionTimeout", machineDetectionTimeout);
            return this;
        }

        /**
         * Whether the request to Url is a GET or a POST. Default is POST. Ignored if ApplicationSid is present.
         * @param method Default is POST. Ignored if ApplicationSid is present.
         * @return
         */
        public Builder method(String method) {
            addItem("Method", method);
            return this;
        }

        /**
         * Whether or not to record a call.
         * @param record Possible values are true or false. Default is false.
         * @return
         */
        public Builder record(Boolean record) {
            addItem("Record", record.toString());
            return this;
        }

        /**
         * The number of channels in the recording.
         * Can be mono (both legs of call recorded under one channel into one recording file) or dual (each leg of call recorded in separate channels into one recording file).
         * @param channel channels for the recording.
         * @return Builder
         */
        public Builder recordingChannels(String channel) {
            addItem("RecordingChannels", channel);
            return this;
        }

        /**
         * The URL to request to when recording is available. See here for the list of parameters passed back to your endpoint.
         * @param recordingStatusCallback The URL to request to when recording is available.
         * @return Builder
         */
        public Builder recordingStatusCallback(String recordingStatusCallback) {
            addItem("RecordingStatusCallback", recordingStatusCallback);
            return this;
        }

        /**
         * Whether the request to RecordingStatusCallback URL is a GET or a POST. Default is POST.
         * @param recordingStatusCallbackMethod RecordingStatusCallBack
         * @return Builder
         */
        public Builder recordingStatusCallbackMethod(String recordingStatusCallbackMethod) {
            addItem("RecordingStatusCallbackMethod", recordingStatusCallbackMethod);
            return this;
        }


        /**
         * Specifies whether to record the inbound audio to SignalWire from the called party or the outbound audio from SignalWire to the called party or both the inbound and outbound audio. Defaults to both
         * @param recordingTrack recordingTrack selection
         * @return Builder
         */
        public Builder recordingTrack(String recordingTrack) {
            addItem("RecordingTrack", recordingTrack);
            return this;
        }

        /**
         * The username to authenticate the caller when making an outbound SIP call.
         * @param sipAuthUsername Username for authentication with SIP
         * @return Builder
         */
        public Builder sipAuthUsername(String sipAuthUsername) {
            addItem("SipAuthUsername", sipAuthUsername);
            return this;
        }

        /**
         * The password to authenticate the caller when making an outbound SIP call.
         * @param sipAuthPassword Password for authentication with SIP
         * @return Builder
         */
        public Builder sipAuthPassword(String sipAuthPassword) {
            addItem("SipAuthPassword", sipAuthPassword);
            return this;
        }

        /**
         * The digits to press after a call is connected. Possible values are (0-9), #, *, and w. Each w gives a 0.5 second pause before moving on to the next instruction.
         * @param digits The digit to press after a call
         * @return Builder
         */
        public Builder sendDigits(String digits) {
            addItem("SendDigits", digits);
            return this;
        }

        /**
         * The URL SignalWire will send webhooks to on every requested StatusCallbackEvent event. See here for the list of parameters passed back to your endpoint.
         * @param statusCallback url to the statusCallback event you are looking to send
         * @return Builder
         */
        public Builder statusCallBack(String statusCallback) {
            addItem("StatusCallback", statusCallback);
            return this;
        }

        /**
         * The status events that trigger a SignalWire webhook.
         * Possible values are initiated, ringing, answered, and completed. To specify multiple events, separate with a space. Default is completed
         * @param statusCallback Possible callBack event like initiated, ringing, answered and completed
         * @return Builder
         */
        public Builder statusCallBackEvent(StatusCallBack statusCallback) {
            addItem("StatusCallbackEvent", statusCallback.name());
            return this;
        }

        /**
         * Whether the request to StatusCallback URL is a GET or a POST. Default is POST. Ignored if ApplicationSid is present.
         * @param statusCallbackMethod status callback method (POST/GET)
         * @return Builder
         */
        public Builder statusCallbackMethod(String statusCallbackMethod) {
            addItem("StatusCallbackMethod", statusCallbackMethod);
            return this;
        }

        /**
         * The time SignalWire will wait before assuming the call has no answer. Max wait time is 600 seconds. Default is 60 seconds.
         * @param timeout timeout length for the call
         * @return Builder
         */
        public Builder timeout(int timeout) {
            addItem("Timeout", String.valueOf(timeout));
            return this;
        }

        /**
         * Whether leading and trailing silence is trimmed from a recording. Possible values are trim-silence and do-not-trim. Default is trim-silence.
         * @param trim Possible values are trim-silence and do-not-trim. Default is trim-silence.
         * @return Builder
         */
        public Builder trim(String trim) {
            addItem("Trim", trim);
            return this;
        }

        public VoiceBuilder build() {
            response = parameters;
            return new VoiceBuilder(this);

        }

        public static void addItem(String name, String value) {
            parameters.put(name, value);

        }

    }


}
