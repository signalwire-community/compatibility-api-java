package io.github.signalwirecommunity.model.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewMessageResponse {
    public final String account_sid;
    public final  String api_version;
    public final  String body;
    public final  int num_segments;
    public final  int num_media;
    public final  String date_created;
    public final  String date_sent;
    public final  String date_updated;
    public final  String direction;
    public final  String error_code;
    public final  String error_message;
    public final  String sid;
    public final  String from;
    public final  String price;
    public final  String price_unit;
    public final  String status;
    public final  String to;
    public final  String uri;

    public NewMessageResponse(@JsonProperty("account_sid") String account_sid,
                              @JsonProperty("api_version") String api_version,
                              @JsonProperty("body") String body,
                              @JsonProperty("num_segments") int num_segments,
                              @JsonProperty("num_media") int num_media,
                              @JsonProperty("date_created") String date_created,
                              @JsonProperty("date_sent") String date_sent,
                              @JsonProperty("date_updated") String date_updated,
                              @JsonProperty("direction") String direction,
                              @JsonProperty("error_code") String error_code,
                              @JsonProperty("error_message")  String error_message,
                              @JsonProperty("sid")  String sid,
                              @JsonProperty("from") String from,
                              @JsonProperty("price") String price,
                              @JsonProperty("price_unit") String price_unit,
                              @JsonProperty("status") String status,
                              @JsonProperty("to") String to,
                              @JsonProperty("uri") String uri) {
        this.account_sid = account_sid;
        this.api_version = api_version;
        this.body = body;
        this.num_segments = num_segments;
        this.num_media = num_media;
        this.date_created = date_created;
        this.date_sent = date_sent;
        this.date_updated = date_updated;
        this.direction = direction;
        this.error_code = error_code;
        this.error_message = error_message;
        this.sid = sid;
        this.from = from;
        this.price = price;
        this.price_unit = price_unit;
        this.status = status;
        this.to = to;
        this.uri = uri;
    }

}
