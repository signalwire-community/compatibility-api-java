package io.github.signalwirecommunity.repository;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
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
            Request request = new Request.Builder()
                    .url(this.baseUrl)
                    .get()
                    .addHeader("Accept", "application/json")
                    .build();

            String response = this.client.getClient().newCall(request).execute().body().string();

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
            HttpUrl.Builder urlBuilder = HttpUrl.parse(this.baseUrl).newBuilder()
                    .addQueryParameter("FriendlyName", friendlyName);

            String url = urlBuilder.build().toString();

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("Accept", "application/json")
                    .build();

            String response = this.client.getClient().newCall(request).execute().body().string();

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
            HttpUrl.Builder urlBuilder = HttpUrl.parse(this.baseUrl).newBuilder()
                    .addQueryParameter("PhoneNumber", phoneNumber);

            String url = urlBuilder.build().toString();

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("Accept", "application/json")
                    .build();

            String response = this.client.getClient().newCall(request).execute().body().string();

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

            HttpResponse<JsonNode> response = Unirest.put(url)
                    .basicAuth(this.projectId, this.apiToken)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .asJson();

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

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("Accept", "application/json")
                    .build();

            String response = this.client.getClient().newCall(request).execute().body().string();

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

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("Accept", "application/json")
                    .build();

            String response = this.client.getClient().newCall(request).execute().body().string();
            return gson.fromJson(response, NumberResponse.class);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Create a new phone number by providing the areaCode and phoneNumber
     *
     * @param areaCode areaCode of the phone number
     * @param phoneNumber value of the phone number to purchase
     * @return PhoneNumber
     */
    @Override
    public PhoneNumber createPhoneNumber(String areaCode, String phoneNumber) {

        try {

            RequestBody formData = new FormBody.Builder()
                    .add("AreaCode", areaCode)
                    .add("PhoneNumber", phoneNumber)
                    .build();

            Request request = new Request.Builder()
                    .url(this.baseUrl)
                    .post(formData)
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();

            String response = this.client.getClient().newCall(request).execute().body().string();
            return gson.fromJson(response, PhoneNumber.class);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * Create a phone number by adding additional params like statusCallback, statuscallbackMethod
     *
     * @param areaCode areaCode of the phone number
     * @param phoneNumber value of the phone number to purchase
     * @param statusCallBack statuscallback link for progress in phone call
     * @return
     */
    @Override
    public PhoneNumber createPhoneNumber(String areaCode, String phoneNumber, String statusCallBack, String friendlyName) {

        try {

            RequestBody formData = new FormBody.Builder()
                    .add("AreaCode", areaCode)
                    .add("PhoneNumber", phoneNumber)
                    .add("StatusCallback", statusCallBack)
                    .add("FriendlyName", friendlyName)
                    .build();

            Request request = new Request.Builder()
                    .url(this.baseUrl)
                    .post(formData)
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();

            String response = this.client.getClient().newCall(request).execute().body().string();
            System.out.println(response);
            return gson.fromJson(response, PhoneNumber.class);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Update a phone number by SID and make changes to the SMS url and voice URL
     *
     * @param sid unique SID for the phone call
     * @param smsUrl SMS url information
     * @param voiceUrl Voice url information
     * @return
     */
    @Override
    public PhoneNumber update(String sid, String smsUrl, String voiceUrl) {
        try {

            HttpUrl.Builder urlBuilder = HttpUrl.parse(this.baseUrl).newBuilder()
                    .addPathSegment(sid);

            String url = urlBuilder.build().toString();

            RequestBody formData = new FormBody.Builder()
                    .add("SmsUrl", smsUrl)
                    .add("VoiceUrl", voiceUrl)
                    .build();

            HttpResponse<JsonNode> response = Unirest.put(url)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .basicAuth(this.projectId, this.apiToken)
                    .body("{ \"SmsUrl\":" + smsUrl +", \"VoiceUrl\":" +voiceUrl+ "}")
                    .asJson();

            String data = response.getBody().toString();

            return gson.fromJson(data, PhoneNumber.class);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Transfer a phone number from one space ID to another
     *
     * @param sid unique SID for the phone call
     * @param accountId unique id for the project from your space
     * @return PhoneNumber
     */
    @Override
    public PhoneNumber transferPhoneNumber(String sid, String accountId) {
        try {

            HttpUrl.Builder urlBuilder = HttpUrl.parse(this.baseUrl).newBuilder()
                    .addPathSegment(sid);

            String url = urlBuilder.build().toString();

            RequestBody formData = new FormBody.Builder()
                    .add("AccountSid", accountId)
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(formData)
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();

            String response = this.client.getClient().newCall(request).execute().body().string();

            return gson.fromJson(response, PhoneNumber.class);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
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

            HttpResponse<JsonNode> response = Unirest.delete(url)
                    .header("Accept", "application/json")
                    .basicAuth(this.projectId, this.apiToken)
                    .asJson();

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
