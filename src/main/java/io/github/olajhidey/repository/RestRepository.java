package io.github.olajhidey.repository;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import io.github.olajhidey.model.SuccessResponse;
import io.github.olajhidey.model.rest.*;
import org.json.JSONObject;

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
     */
    public LookUp phoneLookUp(String phoneNumber) {
        if (phoneNumber != null) {
            try {
                String url = this.spaceName + "/api/relay/rest/lookup/phone_number/";
                HttpResponse<JsonNode> request = Unirest.get(url + "{phone}")
                        .routeParam("phone", phoneNumber)
                        .basicAuth(this.projectId, this.apiToken)
                        .header("accept", "application/json")
                        .asJson();

                if (request.getStatus() == 200) {
                    LookUp lookUp = gson.fromJson(request.getBody().toString(), LookUp.class);
                    return lookUp;
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
     * @param type
     */
    public MfaResponse sendMfa(int type, String to, String from, String message) {

        // if type ==1 {Request a MFA token via text message}
        // if type == 2 {Request a MFA token via phone call}

        String url = this.spaceName + "/api/relay/rest/mfa/";

        return performMfa(url, type, to, from, message);

    }

    public MfaResponse performMfa(String url, int type, String to, String from, String message) {

        String baseUrl = "";

        if (type == 1) {
            baseUrl = url + "sms";
        } else {
            baseUrl = url + "call";
        }

        try {
            String formBody = gson.toJson(new Mfa(to, from, message));
            HttpResponse<JsonNode> request = Unirest.post(baseUrl)
                    .basicAuth(this.projectId, this.apiToken)
                    .header("Content-Type", "application/json")
                    .header("accept", "application/json")
                    .body(formBody)
                    .asJson();

            if (request.getStatus() >= 200 || request.getStatus() <= 204) {
                return gson.fromJson(request.getBody().toString(), MfaResponse.class);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        return null;
    }

    public SuccessResponse verifyMfa(String mfaId, String token) {
        try {
            String url = this.spaceName + "/api/relay/rest/mfa/" + mfaId + "/verify";

            String formBody = gson.toJson(new Mfa.Token(token));

            HttpResponse<JsonNode> request = Unirest.post(url)
                    .basicAuth(this.projectId, this.apiToken)
                    .header("Content-Type", "application/json")
                    .header("accept", "application/json")
                    .body(formBody)
                    .asJson();
            if (request.getStatus() >= 200 || request.getStatus() <= 204) {
                return gson.fromJson(request.getBody().toString(), SuccessResponse.class);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * Returns a list of your Verified Caller IDs. The caller IDs are returned sorted by creation date, with the most recent caller IDs appearing first.
     *
     * @return
     */
    public VerifyCaller listVerifyCallerIds() {
        try {
            String url = this.spaceName + "/api/relay/rest/verified_caller_ids";
            HttpResponse<JsonNode> request = Unirest.get(url)
                    .basicAuth(this.projectId, this.apiToken)
                    .header("accept", "application/json")
                    .asJson();
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
     * @param name
     * @param extension
     * @param number
     * @return
     */
    public VerifyCaller.CallerId createCallerId(String name, String extension, String number) {

        try {
            String url = this.spaceName + "/api/relay/rest/verified_caller_ids";
            String formBody = gson.toJson(new Caller(name, extension, number));

            HttpResponse<JsonNode> request = Unirest.post(url)
                    .basicAuth(this.projectId, this.apiToken)
                    .header("Content-type", "application/json")
                    .header("accept", "application/json")
                    .body(formBody)
                    .asJson();


            if (request.getStatus() >= 200 || request.getStatus() <= 204){
                return gson.fromJson(request.getBody().toString(), VerifyCaller.CallerId.class);
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * To retrieve an existing Verified Caller ID, you send a GET request to the Verified Caller ID resource.
     * Use the unique ID that was returned from your previous request to identify the specific Verified Caller ID.
     *
     * @param id
     * @return
     */
    public VerifyCaller.CallerId getCallerId(String id) {
        try{
            String url = this.spaceName + "/api/relay/rest/verified_caller_ids/"+id;

            HttpResponse<JsonNode> request = Unirest.get(url)
                    .basicAuth(this.projectId, this.apiToken)
                    .header("accept", "application/json")
                    .asJson();


            if (request.getStatus() >= 200 || request.getStatus() <= 204){
                return gson.fromJson(request.getBody().toString(), VerifyCaller.CallerId.class);
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * o update an existing Verified Caller ID, you send a `PUT request to the Verified Caller ID resource. Any parameters not passed in will remain unchanged.
     * @param id
     * @param name
     * @return
     */
    public VerifyCaller.CallerId updateCallerId(String id, String name) {

        try{
            String url = this.spaceName + "/api/relay/rest/verified_caller_ids/"+id;

            JSONObject formBody = new JSONObject();
            formBody.put("name", name);
            System.out.println(formBody);
            HttpResponse<JsonNode> request = Unirest.put(url)
                    .basicAuth(this.projectId, this.apiToken)
                    .header("Content-type", "application/json")
                    .header("accept", "application/json")
                    .body(formBody)
                    .asJson();


            if (request.getStatus() >= 200 || request.getStatus() <= 204){
                return gson.fromJson(request.getBody().toString(), VerifyCaller.CallerId.class);
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * Permanently deletes a Verified Caller ID. You will no longer be able to place calls from this phone number.
     *
     * @param id
     * @return
     */

    public SuccessResponse deleteCallerId(String id) {
        try {
            String url = this.spaceName + "/api/relay/rest/verified_caller_ids/"+id;

            HttpResponse<JsonNode> request = Unirest.delete(url)
                    .basicAuth(this.projectId, this.apiToken)
                    .header("Content-type", "application.json")
                    .header("accept", "application/json")
                    .asJson();

            if (request.getStatus() == 204){
                return new SuccessResponse(true);
            }else{
                return new SuccessResponse(false);
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * Supplies the verification code that was read during the verification call.
     *
     * @param id
     * @param verification_code
     * @return
     */
    public VerifyCaller.CallerId validateCallerId(String id, String verification_code) {

        try{
            String url = this.spaceName + "/api/relay/rest/verified_caller_ids/"+id+"/verification";

            JSONObject formBody = new JSONObject();
            formBody.put("verification_code", verification_code);
            HttpResponse<JsonNode> request = Unirest.put(url)
                    .basicAuth(this.projectId, this.apiToken)
                    .header("Content-type", "application/json")
                    .header("accept", "application/json")
                    .body(formBody)
                    .asJson();

            if (request.getStatus() >= 200 || request.getStatus() <= 204){
                return gson.fromJson(request.getBody().toString(), VerifyCaller.CallerId.class);
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }

        return null;

    }

    public VerifyCaller.CallerId redialVerification(String id) {

        try{
            String url = this.spaceName + "/api/relay/rest/verified_caller_ids/"+id+"/verification";

            HttpResponse<JsonNode> request = Unirest.post(url)
                    .basicAuth(this.projectId, this.apiToken)
                    .header("Content-type", "application/json")
                    .header("accept", "application/json")
                    .asJson();

            if (request.getStatus() >= 200 || request.getStatus() <= 204){
                return gson.fromJson(request.getBody().toString(), VerifyCaller.CallerId.class);
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }

        return null;

    }
}
