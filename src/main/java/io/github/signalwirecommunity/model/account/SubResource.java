package io.github.signalwirecommunity.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubResource {

    public String addresses;
    public String available_phone_numbers;
    public String applications;
    public String authorized_connect_apps;
    public String calls;
    public String conferences;
    public String connect_apps;
    public String incoming_phone_numbers;
    public String keys;
    public String notifications;
    public String outgoing_caller_ids;
    public String queues;
    public String recordings;
    public String sandbox;
    public String signing_keys;
    public String sip;
    public String short_codes;
    public String messages;
    public String transcriptions;
    public String usage;

    public SubResource(@JsonProperty("addresses") String addresses,
                       @JsonProperty("available_phone_numbers") String available_phone_numbers,
                       @JsonProperty("applications") String applications,
                       @JsonProperty("authorized_connect_apps") String authorized_connect_apps,
                       @JsonProperty("calls") String calls,
                       @JsonProperty("conferences") String conferences,
                       @JsonProperty("connect_apps") String connect_apps,
                       @JsonProperty("incoming_phone_numbers") String incoming_phone_numbers,
                       @JsonProperty("keys") String keys,
                       @JsonProperty("notifications") String notifications,
                       @JsonProperty("outgoing_caller_ids") String outgoing_caller_ids,
                       @JsonProperty("queues") String queues,
                       @JsonProperty("recordings") String recordings,
                       @JsonProperty("sandbox") String sandbox,
                       @JsonProperty("signing_keys") String signing_keys,
                       @JsonProperty("sip") String sip,
                       @JsonProperty("short_codes") String short_codes,
                       @JsonProperty("messages") String messages,
                       @JsonProperty("transcriptions") String transcriptions,
                       @JsonProperty("usage") String usage) {

        this.addresses = addresses;
        this.available_phone_numbers = available_phone_numbers;
        this.applications = applications;
        this.authorized_connect_apps = authorized_connect_apps;
        this.calls = calls;
        this.conferences = conferences;
        this.connect_apps = connect_apps;
        this.incoming_phone_numbers = incoming_phone_numbers;
        this.keys = keys;
        this.notifications = notifications;
        this.outgoing_caller_ids = outgoing_caller_ids;
        this.queues = queues;
        this.recordings = recordings;
        this.sandbox = sandbox;
        this.signing_keys = signing_keys;
        this.sip = sip;
        this.short_codes = short_codes;
        this.messages = messages;
        this.transcriptions = transcriptions;
        this.usage = usage;
    }

}


