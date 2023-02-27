package io.github.signalwirecommunity.model.message;

import lombok.Data;

@Data
public class NewMessageResponse {
    public final String account_sid;
    public final String api_version;
    public final String body;
    public final int num_segments;
    public final int num_media;
    public final String date_created;
    public final String date_sent;
    public final String date_updated;
    public final String direction;
    public final String error_code;
    public final String error_message;
    public final String sid;
    public final String from;
    public final String price;
    public final String price_unit;
    public final String status;
    public final String to;
    public final String uri;


}
