package io.github.olajhidey.model.phone;

public class PhoneNumber {
    private String sid;
    private String account_id;
    private String account_sid;
    private String friendly_name;
    private String phone_number;
    private String voice_url;
    private String voice_method;
    private String voice_fallback_url;
    private String voice_fallback_method;
    private String status_callback;
    private String status_callback_method;
    private String voice_application_sid;
    private String voice_caller_id_lookup;
    private String sms_url;
    private String sms_method;
    private String sms_fallback_url;
    private String sms_fallback_method;
    private String sms_application_sid;
    private String date_created;
    private String date_updated;
    private Capabilities capabilities;
    private String beta;
    private String api_version;
    private String address_requirements;
    private String address_sid;
    private String identity_sid;
    private String trunk_sid;
    private String origin;
    private String emergency_address_sid;
    private String emergency_status;
    private String country_code;

    public PhoneNumber(String sid,
                       String account_id,
                       String account_sid,
                       String friendly_name,
                       String phone_number,
                       String voice_url,
                       String voice_method,
                       String voice_fallback_url,
                       String voice_fallback_method,
                       String status_callback,
                       String status_callback_method,
                       String voice_application_sid,
                       String voice_caller_id_lookup,
                       String sms_url, String sms_method,
                       String sms_fallback_url,
                       String sms_fallback_method,
                       String sms_application_sid,
                       String date_created,
                       String date_updated,
                       Capabilities capabilities,
                       String beta,
                       String api_version,
                       String address_requirements,
                       String address_sid,
                       String identity_sid,
                       String trunk_sid,
                       String origin,
                       String emergency_address_sid,
                       String emergency_status,
                       String country_code) {
        this.sid = sid;
        this.account_id = account_id;
        this.account_sid = account_sid;
        this.friendly_name = friendly_name;
        this.phone_number = phone_number;
        this.voice_url = voice_url;
        this.voice_method = voice_method;
        this.voice_fallback_url = voice_fallback_url;
        this.voice_fallback_method = voice_fallback_method;
        this.status_callback = status_callback;
        this.status_callback_method = status_callback_method;
        this.voice_application_sid = voice_application_sid;
        this.voice_caller_id_lookup = voice_caller_id_lookup;
        this.sms_url = sms_url;
        this.sms_method = sms_method;
        this.sms_fallback_url = sms_fallback_url;
        this.sms_fallback_method = sms_fallback_method;
        this.sms_application_sid = sms_application_sid;
        this.date_created = date_created;
        this.date_updated = date_updated;
        this.capabilities = capabilities;
        this.beta = beta;
        this.api_version = api_version;
        this.address_requirements = address_requirements;
        this.address_sid = address_sid;
        this.identity_sid = identity_sid;
        this.trunk_sid = trunk_sid;
        this.origin = origin;
        this.emergency_address_sid = emergency_address_sid;
        this.emergency_status = emergency_status;
        this.country_code = country_code;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getAccount_sid() {
        return account_sid;
    }

    public void setAccount_sid(String account_sid) {
        this.account_sid = account_sid;
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

    public String getVoice_url() {
        return voice_url;
    }

    public void setVoice_url(String voice_url) {
        this.voice_url = voice_url;
    }

    public String getVoice_method() {
        return voice_method;
    }

    public void setVoice_method(String voice_method) {
        this.voice_method = voice_method;
    }

    public String getVoice_fallback_url() {
        return voice_fallback_url;
    }

    public void setVoice_fallback_url(String voice_fallback_url) {
        this.voice_fallback_url = voice_fallback_url;
    }

    public String getVoice_fallback_method() {
        return voice_fallback_method;
    }

    public void setVoice_fallback_method(String voice_fallback_method) {
        this.voice_fallback_method = voice_fallback_method;
    }

    public String getStatus_callback() {
        return status_callback;
    }

    public void setStatus_callback(String status_callback) {
        this.status_callback = status_callback;
    }

    public String getStatus_callback_method() {
        return status_callback_method;
    }

    public void setStatus_callback_method(String status_callback_method) {
        this.status_callback_method = status_callback_method;
    }

    public String getVoice_application_sid() {
        return voice_application_sid;
    }

    public void setVoice_application_sid(String voice_application_sid) {
        this.voice_application_sid = voice_application_sid;
    }

    public String getVoice_caller_id_lookup() {
        return voice_caller_id_lookup;
    }

    public void setVoice_caller_id_lookup(String voice_caller_id_lookup) {
        this.voice_caller_id_lookup = voice_caller_id_lookup;
    }

    public String getSms_url() {
        return sms_url;
    }

    public void setSms_url(String sms_url) {
        this.sms_url = sms_url;
    }

    public String getSms_method() {
        return sms_method;
    }

    public void setSms_method(String sms_method) {
        this.sms_method = sms_method;
    }

    public String getSms_fallback_url() {
        return sms_fallback_url;
    }

    public void setSms_fallback_url(String sms_fallback_url) {
        this.sms_fallback_url = sms_fallback_url;
    }

    public String getSms_fallback_method() {
        return sms_fallback_method;
    }

    public void setSms_fallback_method(String sms_fallback_method) {
        this.sms_fallback_method = sms_fallback_method;
    }

    public String getSms_application_sid() {
        return sms_application_sid;
    }

    public void setSms_application_sid(String sms_application_sid) {
        this.sms_application_sid = sms_application_sid;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(String date_updated) {
        this.date_updated = date_updated;
    }

    public Capabilities getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities;
    }

    public String getBeta() {
        return beta;
    }

    public void setBeta(String beta) {
        this.beta = beta;
    }

    public String getApi_version() {
        return api_version;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    public String getAddress_requirements() {
        return address_requirements;
    }

    public void setAddress_requirements(String address_requirements) {
        this.address_requirements = address_requirements;
    }

    public String getAddress_sid() {
        return address_sid;
    }

    public void setAddress_sid(String address_sid) {
        this.address_sid = address_sid;
    }

    public String getIdentity_sid() {
        return identity_sid;
    }

    public void setIdentity_sid(String identity_sid) {
        this.identity_sid = identity_sid;
    }

    public String getTrunk_sid() {
        return trunk_sid;
    }

    public void setTrunk_sid(String trunk_sid) {
        this.trunk_sid = trunk_sid;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getEmergency_address_sid() {
        return emergency_address_sid;
    }

    public void setEmergency_address_sid(String emergency_address_sid) {
        this.emergency_address_sid = emergency_address_sid;
    }

    public String getEmergency_status() {
        return emergency_status;
    }

    public void setEmergency_status(String emergency_status) {
        this.emergency_status = emergency_status;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }
}
