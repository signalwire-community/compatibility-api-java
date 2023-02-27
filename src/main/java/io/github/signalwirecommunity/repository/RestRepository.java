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
import io.github.signalwirecommunity.model.rest.*;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RestRepository {

    private final String projectId;
    private final String apiToken;
    private final String spaceName;
    private final Gson gson;

    public RestRepository(String projectId, String apiToken, String spaceName) {
        this.projectId = projectId;
        this.apiToken = apiToken;
        this.spaceName = spaceName;
        this.gson = new Gson();
    }

    /**
     * This endpoint allows you to look up validity and formatting information about a number.
     * You can optionally lookup additional information about the number such as carrier and caller ID data.
     *
     * @param phoneNumber Phone number to look up
     * @return LookUp
     */
    public LookUp phoneLookUp(String phoneNumber) {
        if (phoneNumber != null) {
            try {
                String url = this.spaceName + "/api/relay/rest/lookup/phone_number/" + phoneNumber;
                HttpResponse<JsonNode> request = HttpClient.getClient(url, this.projectId, this.apiToken);

                if (request.getStatus() == 200) {
                    return gson.fromJson(request.getBody().toString(), LookUp.class);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Multi-factor authentication
     *
     * @param type type of Multifactor authentication 1 for sms and 2 for voice
     * @return MfaResponse
     */
    public MfaResponse sendMfa(int type, String to, String from, String message) throws SignalWireException {

        // if type ==1 {Request a MFA token via text message}
        // if type == 2 {Request a MFA token via phone call}

        String url = this.spaceName + "/api/relay/rest/mfa/";

        return performMfa(url, type, to, from, message);

    }

    public MfaResponse performMfa(String url, int type, String to, String from, String message) throws SignalWireException{

        String baseUrl = "";

        if (type == 1) {
            baseUrl = url + "sms";
        } else {
            baseUrl = url + "call";
        }

        try {
            String formBody = gson.toJson(new Mfa(to, from, message));
            HttpResponse<JsonNode> request = HttpClient.postClient(baseUrl, this.projectId, this.apiToken, formBody);

            if (request.getStatus() >= 200 || request.getStatus() <= 204) {
                return gson.fromJson(request.getBody().toString(), MfaResponse.class);
            } else {
                ApiException data = gson.fromJson(request.getBody().toString(), ApiException.class);
                throw new SignalWireException(data.getCode(), data.getMessage(), data.getMore_info(), data.getStatus());
            }

        } catch (UnirestException exception) {
            throw new RuntimeException(exception);
        }
    }

    public SuccessResponse verifyMfa(String mfaId, String token) throws SignalWireException{
        try {
            String url = this.spaceName + "/api/relay/rest/mfa/" + mfaId + "/verify";

            String formBody = gson.toJson(new Mfa.Token(token));

            HttpResponse<JsonNode> request = HttpClient.postClient(url, this.projectId, this.apiToken, formBody);
            if (request.getStatus() >= 200 || request.getStatus() <= 204) {
                return gson.fromJson(request.getBody().toString(), SuccessResponse.class);
            } else {
                ApiException data = gson.fromJson(request.getBody().toString(), ApiException.class);
                throw new SignalWireException(data.getCode(), data.getMessage(), data.getMore_info(), data.getStatus());
            }
        } catch (UnirestException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Returns a list of your Verified Caller IDs. The caller IDs are returned sorted by creation date, with the most recent caller IDs appearing first.
     *
     * @return VerifyCaller
     */
    public VerifyCaller listVerifyCallerIds() {
        try {
            String url = this.spaceName + "/api/relay/rest/verified_caller_ids";
            HttpResponse<JsonNode> request = HttpClient.getClient(url, this.projectId, this.apiToken);
            if (request.getStatus() == 200) {
                return gson.fromJson(request.getBody().toString(), VerifyCaller.class);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * To send a verification call to a number, you send a POST request to the Verified Caller ID resource.
     *
     * @param name      Name of the call numbeer
     * @param extension Extension of the phone number
     * @param number    Phone number
     * @return CallerId
     */
    public VerifyCaller.CallerId createCallerId(String name, String extension, String number) throws SignalWireException {

        try {
            String url = this.spaceName + "/api/relay/rest/verified_caller_ids";
            String formBody = gson.toJson(new Caller(name, extension, number));

            HttpResponse<JsonNode> request = HttpClient.postClient(url, this.projectId, this.apiToken, formBody);


            if (request.getStatus() >= 200 || request.getStatus() <= 204) {
                return gson.fromJson(request.getBody().toString(), VerifyCaller.CallerId.class);
            } else {
                ApiException data = gson.fromJson(request.getBody().toString(), ApiException.class);
                throw new SignalWireException(data.getCode(), data.getMessage(), data.getMore_info(), data.getStatus());
            }

        } catch (UnirestException exception) {
            throw new RuntimeException(exception);
        }

    }

    /**
     * To retrieve an existing Verified Caller ID, you send a GET request to the Verified Caller ID resource.
     * Use the unique ID that was returned from your previous request to identify the specific Verified Caller ID.
     *
     * @param id id of the caller Id
     * @return CallerId
     */
    public VerifyCaller.CallerId getCallerId(String id) {
        try {
            String url = this.spaceName + "/api/relay/rest/verified_caller_ids/" + id;

            HttpResponse<JsonNode> request = HttpClient.getClient(url, this.projectId, this.apiToken);

            if (request.getStatus() >= 200 || request.getStatus() <= 204) {
                return gson.fromJson(request.getBody().toString(), VerifyCaller.CallerId.class);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * o update an existing Verified Caller ID, you send a `PUT request to the Verified Caller ID resource. Any parameters not passed in will remain unchanged.
     *
     * @param id   id of the callerID
     * @param name name of the callerId
     * @return CallerId
     */
    public VerifyCaller.CallerId updateCallerId(String id, String name) throws SignalWireException {

        try {
            String url = this.spaceName + "/api/relay/rest/verified_caller_ids/" + id;

            Map<String, Object> formBody = new HashMap<>();
            formBody.put("name", name);

            HttpResponse<JsonNode> request = HttpClient.postClient(url, this.projectId, this.apiToken, formBody);

            if (request.getStatus() >= 200 || request.getStatus() <= 204) {
                return gson.fromJson(request.getBody().toString(), VerifyCaller.CallerId.class);
            } else {
                ApiException data = gson.fromJson(request.getBody().toString(), ApiException.class);
                throw new SignalWireException(data.getCode(), data.getMessage(), data.getMore_info(), data.getStatus());
            }

        } catch (UnirestException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Permanently deletes a Verified Caller ID. You will no longer be able to place calls from this phone number.
     *
     * @param id unique id of the callerId
     * @return SuccessResponse
     */

    public SuccessResponse deleteCallerId(String id) {
        try {
            String url = this.spaceName + "/api/relay/rest/verified_caller_ids/" + id;

            HttpResponse<JsonNode> request = HttpClient.deleteClient(url, this.projectId, this.apiToken);

            if (request.getStatus() == 204) {
                return new SuccessResponse(true);
            } else {
                return new SuccessResponse(false);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * Supplies the verification code that was read during the verification call.
     *
     * @param id                unique id of the Verification ID
     * @param verification_code code sent to either sms or phone
     * @return CalleriD
     */
    public VerifyCaller.CallerId validateCallerId(String id, String verification_code) throws SignalWireException {

        try {
            String url = this.spaceName + "/api/relay/rest/verified_caller_ids/" + id + "/verification";

            JSONObject formBody = new JSONObject();
            formBody.put("verification_code", verification_code);
            HttpResponse<JsonNode> request = HttpClient.postClient(url, this.projectId, this.apiToken, formBody);

            if (request.getStatus() >= 200 || request.getStatus() <= 204) {
                return gson.fromJson(request.getBody().toString(), VerifyCaller.CallerId.class);
            } else {
                ApiException data = gson.fromJson(request.getBody().toString(), ApiException.class);
                throw new SignalWireException(data.getCode(), data.getMessage(), data.getMore_info(), data.getStatus());
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public VerifyCaller.CallerId redialVerification(String id) throws SignalWireException {

        try {
            String url = this.spaceName + "/api/relay/rest/verified_caller_ids/" + id + "/verification";

            HttpResponse<JsonNode> request = HttpClient.postClient(url, this.projectId, this.apiToken, "");

            if (request.getStatus() >= 200 || request.getStatus() <= 204) {
                return gson.fromJson(request.getBody().toString(), VerifyCaller.CallerId.class);
            } else {
                ApiException data = gson.fromJson(request.getBody().toString(), ApiException.class);
                throw new SignalWireException(data.getCode(), data.getMessage(), data.getMore_info(), data.getStatus());
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
