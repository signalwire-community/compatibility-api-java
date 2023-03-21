package io.github.signalwirecommunity.exceptions;

import lombok.Data;

@Data
public class SignalWireException extends Exception {

    private final String code;
    private final String message;
    private final String more_info;
    private final long status;

}
