package io.github.signalwirecommunity.model.account;

public class Account {

    public String sid;
    public String friendly_name;
    public String status;
    public String auth_token;
    public String date_created;
    public String date_updated;
    public String type;
    public String owner_account_sid;
    public String uri;
    public SubResource subresource_uris;
    public Boolean subproject;

    public Account( String sid,
                    String friendly_name,
                    String status,
                    String auth_token,
                    String date_created,
                    String date_updated,
                    String type,
                    String owner_account_sid,
                    String uri,
                    SubResource subresource_uris,
                    Boolean subproject) {
        this.sid = sid;
        this.friendly_name = friendly_name;
        this.status = status;
        this.auth_token = auth_token;
        this.date_created = date_created;
        this.date_updated = date_updated;
        this.type = type;
        this.owner_account_sid = owner_account_sid;
        this.uri = uri;
        this.subresource_uris = subresource_uris;
        this.subproject = subproject;
    }


}
