package io.github.signalwirecommunity.model.account;


import java.util.List;

public class AccountResponse {

    public String uri;
    public String first_page_uri;
    public String next_page_uri;
    public String previous_page_uri;
    public int page;
    public int page_size;
    public List<Account> accounts;

    public AccountResponse(String uri,
                           String first_page_uri,
                           String next_page_uri,
                           String previous_page_uri,
                           int page,
                           int page_size,
                           List<Account> accounts) {
        this.uri = uri;
        this.first_page_uri = first_page_uri;
        this.next_page_uri = next_page_uri;
        this.previous_page_uri = previous_page_uri;
        this.page = page;
        this.page_size = page_size;
        this.accounts = accounts;
    }


}
