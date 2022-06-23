package io.github.olajhidey.model.phone;

import java.util.List;

public class PhoneResponse {

    private String uri;
    private String first_page_uri;
    private String next_page_uri;
    private String previous_page_uri;
    private int page;
    private int page_size;
    private List<PhoneNumber> incoming_phone_numbers;

    public PhoneResponse(String uri,
                         String first_page_uri,
                         String next_page_uri,
                         String previous_page_uri,
                         int page,
                         int page_size,
                         List<PhoneNumber> incoming_phone_numbers) {
        this.uri = uri;
        this.first_page_uri = first_page_uri;
        this.next_page_uri = next_page_uri;
        this.previous_page_uri = previous_page_uri;
        this.page = page;
        this.page_size = page_size;
        this.incoming_phone_numbers = incoming_phone_numbers;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getFirst_page_uri() {
        return first_page_uri;
    }

    public void setFirst_page_uri(String first_page_uri) {
        this.first_page_uri = first_page_uri;
    }

    public String getNext_page_uri() {
        return next_page_uri;
    }

    public void setNext_page_uri(String next_page_uri) {
        this.next_page_uri = next_page_uri;
    }

    public String getPrevious_page_uri() {
        return previous_page_uri;
    }

    public void setPrevious_page_uri(String previous_page_uri) {
        this.previous_page_uri = previous_page_uri;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public List<PhoneNumber> getIncoming_phone_numbers() {
        return incoming_phone_numbers;
    }

    public void setIncoming_phone_numbers(List<PhoneNumber> incoming_phone_numbers) {
        this.incoming_phone_numbers = incoming_phone_numbers;
    }
}
