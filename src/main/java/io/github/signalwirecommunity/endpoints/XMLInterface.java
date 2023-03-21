package io.github.signalwirecommunity.endpoints;

import io.github.signalwirecommunity.exceptions.SignalWireException;
import io.github.signalwirecommunity.model.SuccessResponse;
import io.github.signalwirecommunity.model.laml.Bin;
import io.github.signalwirecommunity.model.laml.LamlResponse;

public interface XMLInterface {
    LamlResponse list();

    LamlResponse list(String friendlyName);

    Bin create(String name, String contents) throws SignalWireException;

    Bin get(String sid);

    Bin update(String sid, String name, String contents) throws SignalWireException;

    SuccessResponse delete(String sid);
}
