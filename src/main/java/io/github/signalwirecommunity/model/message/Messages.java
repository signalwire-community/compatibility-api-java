package io.github.signalwirecommunity.model.message;


import java.util.List;


/**
 * Model to get response from API Get Messages
 */

public class Messages {

    public final String uri;
    public final String first_page_uri;
    public final List<Message> messages;

    public Messages(String uri, String first_page_uri, List<Message> messages) {
        this.uri = uri;
        this.first_page_uri = first_page_uri;
        this.messages = messages;
    }

}
