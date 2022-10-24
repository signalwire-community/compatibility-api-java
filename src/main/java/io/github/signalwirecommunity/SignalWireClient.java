package io.github.signalwirecommunity;

import io.github.signalwirecommunity.repository.*;

public class SignalWireClient {
    private final String projectID;
    private final String apiToken;
    private final String spaceName;
    public SignalWireClient(String projectID, String apiToken, String spaceName) {
        this.projectID = projectID;
        this.apiToken = apiToken;
        this.spaceName = urlGenerator(spaceName);
    }

    public MessageRepository message() {
        return new MessageRepository(this.projectID, this.apiToken, this.spaceName);
    }

    public VoiceRepository voice() {
        return new VoiceRepository(this.projectID, this.apiToken, this.spaceName);
    }

    public LamlRepository xml() {
        return new LamlRepository(this.projectID, this.apiToken, this.spaceName);
    }

    public SipRepository sip() {
        return new SipRepository(this.projectID, this.apiToken, this.spaceName);
    }

    public AccountRepository accounts() {
        return new AccountRepository(this.projectID, this.apiToken, this.spaceName);
    }

    public PhoneRepository phone() {
        return new PhoneRepository(this.projectID, this.apiToken, this.spaceName);
    }

    public RestRepository rest() {
        return new RestRepository(this.projectID, this.apiToken, this.spaceName);
    }

    public static String urlGenerator(String spaceName) {
        return String.format("https://%s.signalwire.com", spaceName).toLowerCase().trim();
    }

}
