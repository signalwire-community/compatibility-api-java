package io.github.signalwirecommunity.model.rest;

import java.util.List;

public class SipResponse {
    public Links links;
    public List<Sip> data;

    public SipResponse(Links links, List<Sip> data) {
        this.links = links;
        this.data = data;
    }

    static public class Sip{
        public String id;
        public String username;
        public String caller_id;
        public String send_as;
        public List<String> ciphers;
        public List<String> codecs;
        public String encryption;
        public String type;

        public Sip(String id, String username, String caller_id, String send_as, List<String> ciphers, List<String> codecs, String encryption, String type) {
            this.id = id;
            this.username = username;
            this.caller_id = caller_id;
            this.send_as = send_as;
            this.ciphers = ciphers;
            this.codecs = codecs;
            this.encryption = encryption;
            this.type = type;
        }
    }
}
