package io.github.signalwirecommunity.model.rest;

import lombok.Data;

import java.util.List;

@Data
public class SipResponse {
    public Links links;
    public List<Sip> data;

    @Data
    static public class Sip{
        public String id;
        public String username;
        public String caller_id;
        public String send_as;
        public List<String> ciphers;
        public List<String> codecs;
        public String encryption;
        public String type;

    }
}
