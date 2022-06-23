package io.github.olajhidey.endpoints;

import io.github.olajhidey.model.SuccessResponse;
import io.github.olajhidey.model.phone.NumberResponse;
import io.github.olajhidey.model.phone.PhoneNumber;
import io.github.olajhidey.model.phone.PhoneResponse;

public interface PhoneInterface {

    PhoneResponse getPhoneNumbers();

    PhoneResponse getPhoneNumbers(String friendlyName);

    PhoneResponse getPhoneNumber(String phoneNumber);

    PhoneNumber getPhoneNumberBySid(String sid);

    NumberResponse getTollFreeNumbers(String isoCountry);

    NumberResponse getLocalNumbers(String isoCountry);

    PhoneNumber createPhoneNumber(String areaCode, String phoneNumber);

    PhoneNumber createPhoneNumber(String areaCode, String phoneNumber, String statusCallBack, String friendlyName);

    PhoneNumber update(String sid, String smsUrl, String voiceUrl);

    PhoneNumber transferPhoneNumber(String sid, String accountId);

    SuccessResponse deletePhone(String sid);

}
