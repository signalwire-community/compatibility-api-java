package io.github.olajhidey.endpoints;

import io.github.olajhidey.model.SuccessResponse;
import io.github.olajhidey.model.laml.Bin;
import io.github.olajhidey.model.laml.LamlResponse;

public interface XMLInterface {
    LamlResponse list();

    LamlResponse list(String friendlyName);

    Bin create(String name, String contents);

    Bin get(String sid);

    Bin update(String sid, String name, String contents);

    SuccessResponse delete(String sid);
}
