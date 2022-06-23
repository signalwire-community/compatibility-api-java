package io.github.olajhidey.model.rest;

public class Mfa {
    public String to;
    public String from;
    public String message;

    public Mfa(String to, String from, String message) {
        this.to = to;
        this.from = from;
        this.message = message;
    }

    static public class Token{
        public String token;

        public Token(String token) {
            this.token = token;
        }
    }
}
