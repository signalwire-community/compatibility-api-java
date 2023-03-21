package io.github.signalwirecommunity.model.call;

import lombok.Data;

import java.util.List;

@Data
public class CallResponse {
    private String uri;
    private String first_page_uri;
    private String next_page_uri;
    private String previous_page_uri;
    private int page;
    private int page_size;
    private List<Call> calls;

}
