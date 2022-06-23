package io.github.olajhidey.model.message;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    public final String api_version;
    public final String account_sid;
    public final String body;
    public final String status;
    public final String to;
    public final String from;
    public final String price_unit;
    public final String sid;
    public final String date_created;
    public final String date_sent;
    public final String date_updated;
    public final String direction;
    public final String price;

    public Message(
            @JsonProperty("api_version") String api_version,
            @JsonProperty("account_sid") String account_sid,
            @JsonProperty("body") String body,
            @JsonProperty("status") String status,
            @JsonProperty("to") String to,
            @JsonProperty("from") String from,
            @JsonProperty("price_unit") String price_unit,
            @JsonProperty("sid") String sid,
            @JsonProperty("date_created") String date_created,
            @JsonProperty("date_sent") String date_sent,
            @JsonProperty("date_updated") String date_updated,
            @JsonProperty("direction") String direction,
            @JsonProperty("price") String price) {
        this.account_sid = account_sid;
        this.body = body;
        this.status = status;
        this.to = to;
        this.from = from;
        this.price_unit = price_unit;
        this.sid = sid;
        this.date_created = date_created;
        this.date_sent = date_sent;
        this.date_updated = date_updated;
        this.direction = direction;
        this.price = price;
        this.api_version = api_version;
    }

}
