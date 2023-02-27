package io.github.signalwirecommunity.model.message;


import lombok.Data;

import java.util.List;


/**
 * Model to get response from API Get Messages
 */

@Data
public class Messages {

    public final String uri;
    public final String first_page_uri;
    public final List<Message> messages;

}
