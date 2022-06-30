package io.github.signalwirecommunity.model.laml;

public class Bin {
    public final String sid;
    public final String date_created;
    public final String date_updated;
    public final String date_last_accessed;
    public final String account_sid;
    public final String name;
    public final String contents;
    public final String request_url;
    public final int num_requests;
    public final String api_version;
    public final String uri;

    public Bin(String sid, String date_created, String date_updated, String date_last_accessed, String account_sid, String name, String contents, String request_url, int num_requests, String api_version, String uri) {
        this.sid = sid;
        this.date_created = date_created;
        this.date_updated = date_updated;
        this.date_last_accessed = date_last_accessed;
        this.account_sid = account_sid;
        this.name = name;
        this.contents = contents;
        this.request_url = request_url;
        this.num_requests = num_requests;
        this.api_version = api_version;
        this.uri = uri;
    }
}
