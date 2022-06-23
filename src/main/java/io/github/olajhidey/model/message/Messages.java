package io.github.olajhidey.model.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * Model to get response from API Get Messages
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Messages {

    public final String uri;
    public final String first_page_uri;
    public final List<Message> messages;

    public Messages(@JsonProperty("uri") String uri, @JsonProperty("first_page_uri") String first_page_uri, @JsonProperty("messages") List<Message> messages){
        this.uri = uri;
        this.first_page_uri = first_page_uri;
        this.messages = messages;
    }

}
