package io.github.signalwirecommunity.model.laml;

import lombok.Data;

@Data
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
}
