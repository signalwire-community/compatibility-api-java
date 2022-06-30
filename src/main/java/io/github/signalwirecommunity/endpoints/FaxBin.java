package io.github.signalwirecommunity.endpoints;

import io.github.signalwirecommunity.model.xml.FaxResponse;

import java.util.HashMap;

public interface FaxBin {

    FaxResponse.Builder receive(HashMap<String, String> faxAttribute);

    FaxResponse.Builder reject();

}
