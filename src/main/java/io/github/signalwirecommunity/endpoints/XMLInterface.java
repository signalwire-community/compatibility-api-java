package io.github.signalwirecommunity.endpoints;

import io.github.signalwirecommunity.model.SuccessResponse;
import io.github.signalwirecommunity.model.laml.Bin;
import io.github.signalwirecommunity.model.laml.LamlResponse;

public interface XMLInterface {
    LamlResponse list();

    LamlResponse list(String friendlyName);

    Bin create(String name, String contents);

    Bin get(String sid);

    Bin update(String sid, String name, String contents);

    SuccessResponse delete(String sid);
}
