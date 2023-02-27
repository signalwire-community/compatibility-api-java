package io.github.signalwirecommunity.model.message;


import lombok.Data;

@Data
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

}
