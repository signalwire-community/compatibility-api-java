package io.github.signalwirecommunity.endpoints;

import io.github.signalwirecommunity.model.xml.MessageResponse;

import java.util.HashMap;

public interface MessageBin {

    MessageResponse.Builder message(HashMap<String, String> messageAttribute, String text);

    MessageResponse.Builder messageWithMedia(HashMap<String, String> messageAttribute, String body, String mediaUrl);

    MessageResponse.Builder redirect(String url);

}
