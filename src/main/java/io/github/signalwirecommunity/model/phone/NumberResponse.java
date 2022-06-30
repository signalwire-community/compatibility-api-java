package io.github.signalwirecommunity.model.phone;

import java.util.List;

public class NumberResponse {
    private String uri;
    private List<AvailableNumbers> available_phone_numbers;

    public NumberResponse(String uri, List<AvailableNumbers> available_phone_numbers) {
        this.uri = uri;
        this.available_phone_numbers = available_phone_numbers;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<AvailableNumbers> getAvailable_phone_numbers() {
        return available_phone_numbers;
    }

    public void setAvailable_phone_numbers(List<AvailableNumbers> available_phone_numbers) {
        this.available_phone_numbers = available_phone_numbers;
    }
}
