package io.github.signalwirecommunity.model.message;




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
            String api_version,
            String account_sid,
            String body,
            String status,
            String to,
            String from,
            String price_unit,
            String sid,
            String date_created,
            String date_sent,
            String date_updated,
            String direction,
            String price) {
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
