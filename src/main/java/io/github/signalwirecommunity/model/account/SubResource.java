package io.github.signalwirecommunity.model.account;


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

    public SubResource(String addresses,
                       String available_phone_numbers,
                       String applications,
                       String authorized_connect_apps,
                       String calls,
                       String conferences,
                       String connect_apps,
                       String incoming_phone_numbers,
                       String keys,
                       String notifications,
                       String outgoing_caller_ids,
                       String queues,
                       String recordings,
                       String sandbox,
                       String signing_keys,
                       String sip,
                       String short_codes,
                       String messages,
                       String transcriptions,
                       String usage) {

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


