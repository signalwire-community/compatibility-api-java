package io.github.signalwirecommunity.repository;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.github.signalwirecommunity.exceptions.ApiException;
import io.github.signalwirecommunity.exceptions.SignalWireException;
import io.github.signalwirecommunity.http.HttpClient;
import io.github.signalwirecommunity.model.SuccessResponse;
import io.github.signalwirecommunity.endpoints.PhoneInterface;
import io.github.signalwirecommunity.http.RestClient;
import io.github.signalwirecommunity.model.phone.NumberResponse;
import io.github.signalwirecommunity.model.phone.PhoneNumber;
import io.github.signalwirecommunity.model.phone.PhoneResponse;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PhoneRepository implements PhoneInterface {


    private String projectId;
    private String apiToken;
    private String spaceUrl;
    private RestClient client;
    private String baseUrl;
    private Gson gson;

    private String availableNumbers;

    public PhoneRepository(String projectId,
                           String apiToken,
                           String spaceUrl
    ) {
        this.projectId = projectId;
        this.apiToken = apiToken;
        this.spaceUrl = spaceUrl;
        this.client = new RestClient(projectId, apiToken);
        this.baseUrl = this.spaceUrl + "/api/laml/2010-04-01/Accounts/" + projectId + "/IncomingPhoneNumbers";
        this.availableNumbers = this.spaceUrl + "/api/laml/2010-04-01/Accounts/" + projectId + "/AvailablePhoneNumbers";
        this.gson = new Gson();
    }

    /**
     * Get all the phone numbers in the project specified
     *
     * @return PhoneResponse
     */
    @Override
    public PhoneResponse getPhoneNumbers() {

        try {
            HttpResponse<JsonNode> client = HttpClient.getClient(this.baseUrl, this.projectId, this.apiToken);

            String response = client.getBody().toString();

            return gson.fromJson(response, PhoneResponse.class);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Get list of phone numbers by the friendlyName
     *
     * @param friendlyName name to query
     * @return PhoneResponse
     */
    @Override
    public PhoneResponse getPhoneNumbers(String friendlyName) {

        try {

            Map<String, Object> params = new HashMap<>();
            params.put("FriendlyName", friendlyName);

            HttpResponse<JsonNode> client = HttpClient.getClient(baseUrl, this.projectId, this.apiToken, params);

            String response = client.getBody().toString();

            return gson.fromJson(response, PhoneResponse.class);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * Get list of phone number in a project by PhoneNumber
     *
     * @param phoneNumber phone number to query
     * @return PhoneResponse
     */
    @Override
    public PhoneResponse getPhoneNumber(String phoneNumber) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("PhoneNumber", phoneNumber);

            HttpResponse<JsonNode> client = HttpClient.getClient(this.baseUrl, this.projectId, this.apiToken, params);

            String response = client.getBody().toString();

            return gson.fromJson(response, PhoneResponse.class);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * Get List of phone number in the project by SID
     *
     * @param sid unique SID of the phone number
     * @return PhoneNumber
     */
    @Override
    public PhoneNumber getPhoneNumberBySid(String sid) {
        try {
            HttpUrl.Builder urlBuilder = HttpUrl.parse(this.baseUrl).newBuilder()
                    .addPathSegment(sid);

            String url = urlBuilder.build().toString();

            HttpResponse<JsonNode> response = HttpClient.getClient(url, this.projectId, this.apiToken);

            String data = response.getBody().toString();

            return gson.fromJson(data, PhoneNumber.class);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Get list of Toll Free numbers by ISO country value
     *
     * @param isoCountry ISO value of the country
     * @return NumberResponse
     */
    @Override
    public NumberResponse getTollFreeNumbers(String isoCountry) {
        try {

            HttpUrl.Builder urlBuilder = HttpUrl.parse(this.availableNumbers).newBuilder()
                    .addPathSegment(isoCountry)
                    .addPathSegment("TollFree");

            String url = urlBuilder.build().toString();

            HttpResponse<JsonNode> client = HttpClient.getClient(url, this.projectId, this.apiToken);

            String response = client.getBody().toString();

            return gson.fromJson(response, NumberResponse.class);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Get list of local numbers by ISO country
     *
     * @param isoCountry ISO value of the country
     * @return NumberResponse
     */
    @Override
    public NumberResponse getLocalNumbers(String isoCountry) {
        try {

            HttpUrl.Builder urlBuilder = HttpUrl.parse(this.availableNumbers).newBuilder()
                    .addPathSegment(isoCountry)
                    .addPathSegment("Local");

            String url = urlBuilder.build().toString();

            HttpResponse<JsonNode> client = HttpClient.getClient(url, this.projectId, this.apiToken);

            String response = client.getBody().toString();
            return gson.fromJson(response, NumberResponse.class);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Create a new phone number by providing the areaCode and phoneNumber
     *
     * @param areaCode    areaCode of the phone number
     * @param phoneNumber value of the phone number to purchase
     * @return PhoneNumber
     */
    @Override
    public PhoneNumber createPhoneNumber(String areaCode, String phoneNumber) throws SignalWireException {

        try {

            HashMap<String, Object> form = new HashMap<>();

            form.put("AreaCode", areaCode);
            form.put("PhoneNumber", phoneNumber);

            HttpResponse<JsonNode> request = HttpClient.postClient(this.baseUrl, this.projectId, this.apiToken, form);

            if (request.getStatus() >= 200 && request.getStatus() <= 204) {
                String response = request.getBody().toString();
                return gson.fromJson(response, PhoneNumber.class);
            } else {
                ApiException exception = gson.fromJson(request.getBody().toString(), ApiException.class);
                throw new SignalWireException(exception.getCode(), exception.getMessage(), exception.getMore_info(), exception.getStatus());
            }

        } catch (UnirestException exception) {
            throw new RuntimeException(exception);
        }

    }

    /**
     * Create a phone number by adding additional params like statusCallback, statuscallbackMethod
     *
     * @param areaCode       areaCode of the phone number
     * @param phoneNumber    value of the phone number to purchase
     * @param statusCallBack statuscallback link for progress in phone call
     * @return
     */
    @Override
    public PhoneNumber createPhoneNumber(String areaCode, String phoneNumber, String statusCallBack, String friendlyName) throws SignalWireException {

        try {

            Map<String, Object> formData = new HashMap<>();
            formData.put("AreaCode", areaCode);
            formData.put("PhoneNumber", phoneNumber);
            formData.put("StatusCallback", statusCallBack);
            formData.put("FriendlyName", friendlyName);

            HttpResponse<JsonNode> request = HttpClient.postClient(this.baseUrl, this.projectId, this.apiToken, formData);


            if (request.getStatus() >= 200 && request.getStatus() <= 204) {
                String response = request.getBody().toString();
                return gson.fromJson(response, PhoneNumber.class);
            } else {
                ApiException exception = gson.fromJson(request.getBody().toString(), ApiException.class);
                throw new SignalWireException(exception.getCode(), exception.getMessage(), exception.getMore_info(), exception.getStatus());
            }


        } catch (UnirestException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Update a phone number by SID and make changes to the SMS url and voice URL
     *
     * @param sid      unique SID for the phone call
     * @param smsUrl   SMS url information
     * @param voiceUrl Voice url information
     * @return
     */
    @Override
    public PhoneNumber update(String sid, String smsUrl, String voiceUrl) throws SignalWireException {
        try {

            HttpUrl.Builder urlBuilder = HttpUrl.parse(this.baseUrl).newBuilder()
                    .addPathSegment(sid);

            String url = urlBuilder.build().toString();

            Map<String, Object> data = new HashMap<>();
            data.put("SmsUrl", smsUrl);
            data.put("VoiceUrl", voiceUrl);

            HttpResponse<JsonNode> client = HttpClient.postClient(url, this.projectId, this.apiToken, data);

            String response = client.getBody().toString();

            if (client.getStatus() >= 200 && client.getStatus() <= 204) {
                return gson.fromJson(response, PhoneNumber.class);
            } else {
                ApiException exception = gson.fromJson(response, ApiException.class);
                throw new SignalWireException(exception.getCode(), exception.getMessage(), exception.getMore_info(), exception.getStatus());
            }

        } catch (UnirestException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Transfer a phone number from one space ID to another
     *
     * @param sid       unique SID for the phone call
     * @param accountId unique id for the project from your space
     * @return PhoneNumber
     */
    @Override
    public PhoneNumber transferPhoneNumber(String sid, String accountId) throws SignalWireException {
        try {

            HttpUrl.Builder urlBuilder = HttpUrl.parse(this.baseUrl).newBuilder()
                    .addPathSegment(sid);

            String url = urlBuilder.build().toString();

            Map<String, Object> form = new HashMap<>();
            form.put("AccountSid", accountId);

            HttpResponse<JsonNode> client = HttpClient.postClient(url, this.projectId, this.apiToken, form);

            String response = client.getBody().toString();

            if (client.getStatus() >= 200 && client.getStatus() <= 204) {
                return gson.fromJson(response, PhoneNumber.class);
            } else {
                ApiException exception = gson.fromJson(response, ApiException.class);
                throw new SignalWireException(exception.getCode(), exception.getMessage(), exception.getMore_info(), exception.getStatus());
            }
        } catch (UnirestException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Delete a phone number from the project or a Space
     *
     * @param sid unique SID for the phone call
     * @return SuccessResponse
     */
    @Override
    public SuccessResponse deletePhone(String sid) {
        try {
            HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder()
                    .addPathSegment(sid);

            String url = urlBuilder.build().toString();

            HttpResponse<JsonNode> response = HttpClient.deleteClient(url, this.projectId, this.apiToken);
            if (response.getStatus() == 204) {
                return new SuccessResponse(true);
            } else {
                return gson.fromJson(response.getBody().toString(), SuccessResponse.class);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
