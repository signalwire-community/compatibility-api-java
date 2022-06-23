package io.github.olajhidey.model.rest;

import java.util.List;

public class VerifyCaller {

    public Links links;
    public List<CallerId> data;

    public VerifyCaller(Links links, List<CallerId> data) {
        this.links = links;
        this.data = data;
    }

    static public class CallerId{
        public String type;
        public String id;
        public String name;
        public String extension;
        public String number;
        public String verified;
        public String verified_at;
        public String status;

        public CallerId(String type, String id, String name, String extension, String number, String verified, String verified_at, String status) {
            this.type = type;
            this.id = id;
            this.name = name;
            this.extension = extension;
            this.number = number;
            this.verified = verified;
            this.verified_at = verified_at;
            this.status = status;
        }
    }
}
