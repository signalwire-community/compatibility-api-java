package io.github.signalwirecommunity.endpoints;

import io.github.signalwirecommunity.exceptions.SignalWireException;
import io.github.signalwirecommunity.model.SuccessResponse;
import io.github.signalwirecommunity.model.phone.NumberResponse;
import io.github.signalwirecommunity.model.phone.PhoneNumber;
import io.github.signalwirecommunity.model.phone.PhoneResponse;

public interface PhoneInterface {

    PhoneResponse getPhoneNumbers();

    PhoneResponse getPhoneNumbers(String friendlyName);

    PhoneResponse getPhoneNumber(String phoneNumber);

    PhoneNumber getPhoneNumberBySid(String sid);

    NumberResponse getTollFreeNumbers(String isoCountry);

    NumberResponse getLocalNumbers(String isoCountry);

    PhoneNumber createPhoneNumber(String areaCode, String phoneNumber) throws SignalWireException;

    PhoneNumber createPhoneNumber(String areaCode, String phoneNumber, String statusCallBack, String friendlyName) throws SignalWireException;

    PhoneNumber update(String sid, String smsUrl, String voiceUrl) throws SignalWireException;

    PhoneNumber transferPhoneNumber(String sid, String accountId) throws SignalWireException;

    SuccessResponse deletePhone(String sid);

}
