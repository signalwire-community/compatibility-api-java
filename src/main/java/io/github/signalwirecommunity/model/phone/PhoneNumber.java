package io.github.signalwirecommunity.model.phone;

import lombok.Data;

@Data
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
}
