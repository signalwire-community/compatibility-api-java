package io.github.signalwirecommunity.model.rest;

public class MfaResponse {
    public String id;
    public boolean success;
    public String to;
    public String channel;

    public MfaResponse(String id, boolean success, String to, String channel) {
        this.id = id;
        this.success = success;
        this.to = to;
        this.channel = channel;
    }
}
