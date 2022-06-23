package io.github.olajhidey.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountResponse {

    public String uri;
    public String first_page_uri;
    public String next_page_uri;
    public String previous_page_uri;
    public int page;
    public int page_size;
    public List<Account> accounts;

    public AccountResponse(@JsonProperty("uri") String uri, @JsonProperty("first_page_uri") String first_page_uri, @JsonProperty("next_page_uri") String next_page_uri, @JsonProperty("previous_page_uri") String previous_page_uri, @JsonProperty("page") int page, @JsonProperty("page_size") int page_size, @JsonProperty("accounts") List<Account> accounts) {
        this.uri = uri;
        this.first_page_uri = first_page_uri;
        this.next_page_uri = next_page_uri;
        this.previous_page_uri = previous_page_uri;
        this.page = page;
        this.page_size = page_size;
        this.accounts = accounts;
    }


}
