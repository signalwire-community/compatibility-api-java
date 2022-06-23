package io.github.olajhidey.endpoints;

import io.github.olajhidey.model.xml.MessageResponse;

import java.util.HashMap;

public interface MessageBin {

    MessageResponse.Builder message(HashMap<String, String> messageAttribute, String text);

    MessageResponse.Builder messageWithMedia(HashMap<String, String> messageAttribute, String body, String mediaUrl);

    MessageResponse.Builder redirect(String url);

}
