package io.github.signalwirecommunity.model.laml;

public class BinMetadata {
    public final String first_page_url;
    public final String key;
    public final int page;
    public final int page_size;
    public final String previous_page_url;
    public final String url;

    public BinMetadata(String first_page_url, String key, int page, int page_size, String previous_page_url, String url) {
        this.first_page_url = first_page_url;
        this.key = key;
        this.page = page;
        this.page_size = page_size;
        this.previous_page_url = previous_page_url;
        this.url = url;
    }


}
