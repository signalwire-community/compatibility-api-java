package io.github.olajhidey.endpoints;

import io.github.olajhidey.model.xml.FaxResponse;

import java.util.HashMap;

public interface FaxBin {

    FaxResponse.Builder receive(HashMap<String, String> faxAttribute);

    FaxResponse.Builder reject();

}
