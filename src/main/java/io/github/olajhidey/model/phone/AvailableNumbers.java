package io.github.olajhidey.model.phone;

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

    public AvailableNumbers(String friendly_name,
                            String phone_number,
                            String lata,
                            String locality,
                            String rate_center,
                            String latitude,
                            String longitude,
                            String region,
                            String postal_code,
                            String iso_country,
                            Capabilities capabilities,
                            Boolean beta) {
        this.friendly_name = friendly_name;
        this.phone_number = phone_number;
        this.lata = lata;
        this.locality = locality;
        this.rate_center = rate_center;
        this.latitude = latitude;
        this.longitude = longitude;
        this.region = region;
        this.postal_code = postal_code;
        this.iso_country = iso_country;
        this.capabilities = capabilities;
        this.beta = beta;
    }

    public String getFriendly_name() {
        return friendly_name;
    }

    public void setFriendly_name(String friendly_name) {
        this.friendly_name = friendly_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getLata() {
        return lata;
    }

    public void setLata(String lata) {
        this.lata = lata;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getRate_center() {
        return rate_center;
    }

    public void setRate_center(String rate_center) {
        this.rate_center = rate_center;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getIso_country() {
        return iso_country;
    }

    public void setIso_country(String iso_country) {
        this.iso_country = iso_country;
    }

    public Capabilities getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities;
    }

    public Boolean getBeta() {
        return beta;
    }

    public void setBeta(Boolean beta) {
        this.beta = beta;
    }
}
