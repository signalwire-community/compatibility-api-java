package io.github.signalwirecommunity.model.laml;

import java.util.List;

public class LamlResponse {
    public final BinMetadata meta;
    public final List<Bin> laml_bins;

    public LamlResponse(BinMetadata meta, List<Bin> laml_bins){
        this.meta = meta;
        this.laml_bins = laml_bins;
    }
}
