package io.github.olajhidey.model.rest;

public class Links {

    public String self;
    public String first;
    public String next;
    public String prev;

    public Links(String self, String first, String next, String prev) {
        this.self = self;
        this.first = first;
        this.next = next;
        this.prev = prev;
    }
}
