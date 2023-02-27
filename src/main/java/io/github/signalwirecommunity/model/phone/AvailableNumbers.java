package io.github.signalwirecommunity.model.phone;

import lombok.Data;

@Data
public class AvailableNumbers {

    private String friendly_name;
    private String phone_number;
    private String lata;
    private String locality;
    private String rate_center;
    private String latitude;
    private String longitude;
    private String region;
    private String postal_code;
    private String iso_country;
    private Capabilities capabilities;
    private Boolean beta;

}
