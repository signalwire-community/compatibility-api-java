package io.github.olajhidey.endpoints;

import io.github.olajhidey.model.xml.VoiceResponse;

import java.util.HashMap;
import java.util.List;

public interface VoiceBin {

    VoiceResponse.Builder deniose(int type);

    VoiceResponse.Builder dial(HashMap<String, String> dialAttribute, String phone);

    VoiceResponse.Builder dialWithNumberBin(HashMap<String, String> dialAttribute, String phoneNumber, HashMap<String, String> numberAttribute);

    VoiceResponse.Builder dialWithNumberBin(HashMap<String, String> dialAttribute, List<String> phoneNumber);

    VoiceResponse.Builder dialWithSIPBin(HashMap<String, String> dialAttribute, String destination, HashMap<String, String> sipAttribute);

    VoiceResponse.Builder dialWithQueueBin(HashMap<String, String> dialAttribute, String text, HashMap<String, String> queueAttribute);

    VoiceResponse.Builder dialWithConferenceBin(HashMap<String, String> dialAttribute, String text, HashMap<String, String> conferenceAttribute);

    VoiceResponse.Builder echo(String timeout);

    VoiceResponse.Builder enqueue(HashMap<String, String> enqueueAttr, String text);

    VoiceResponse.Builder gatherWithSayBin(HashMap<String, String> gatherAttr, String text);

    VoiceResponse.Builder gatherWithPlayBin(HashMap<String, String> gatherAttr, String url);

    VoiceResponse.Builder hangup();

    VoiceResponse.Builder leave();

    VoiceResponse.Builder pause(String length);

    VoiceResponse.Builder play(HashMap<String, String> playAttribute, String url);

    VoiceResponse.Builder record(HashMap<String, String> recordAttribute);

    VoiceResponse.Builder redirect(String url);

    VoiceResponse.Builder refer(HashMap<String, String> referAttribute, String sipAddress);

    VoiceResponse.Builder reject(String reason);

    VoiceResponse.Builder say(HashMap<String, String> sayAttribute, String text);

    VoiceResponse.Builder sms(HashMap<String, String> smsAttribute, String text);

    VoiceResponse.Builder stream(HashMap<String, String> streamAttribute);

    VoiceResponse.Builder stopStream(HashMap<String, String> streamAttribute);

}
