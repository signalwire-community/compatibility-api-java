package io.github.olajhidey.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
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

    public Account(@JsonProperty("sid") String sid, @JsonProperty("friendly_name") String friendly_name, @JsonProperty("status") String status, @JsonProperty("auth_token") String auth_token, @JsonProperty("date_created") String date_created, @JsonProperty("date_updated") String date_updated, @JsonProperty("type") String type, @JsonProperty("owner_account_sid") String owner_account_sid, @JsonProperty("uri") String uri, @JsonProperty("subresource_uris") SubResource subresource_uris, @JsonProperty("subproject") Boolean subproject) {
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
