package io.github.signalwirecommunity.model.phone;

import lombok.Data;

import java.util.List;

@Data
public class NumberResponse {
    private String uri;
    private List<AvailableNumbers> available_phone_numbers;
}
