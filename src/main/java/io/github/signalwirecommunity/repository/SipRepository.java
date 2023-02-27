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
import io.github.signalwirecommunity.model.rest.SipResponse;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SipRepository {

    private final String projectId;
    private final String apiToken;
    private final String spaceName;
    private final Gson gson;

    public SipRepository(String projectId, String apiToken, String spaceName) {
        this.projectId = projectId;
        this.apiToken = apiToken;
        this.spaceName = spaceName;
        this.gson = new Gson();
    }

    /**
     * Returns a list of your SIP Endpoints.
     * The endpoints are returned sorted by creation date, with the most recent endpoints appearing first.
     * The list is filterable by sending in any of the following parameters.
     *
     * @return SipResponse
     */
    public SipResponse list() {

        String url = this.spaceName + "/api/relay/rest/endpoints/sip";

        HttpResponse<JsonNode> request = null;
        try {
            request = Unirest.get(url)
                    .basicAuth(this.projectId, this.apiToken)
                    .header("accept", "application/json")
                    .asJson();

            if (request.getStatus() == 200) {
                return this.gson.fromJson(request.getBody().toString(), SipResponse.class);
            }
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * To create a new SIP Endpoint, you send a POST request to the SIP Endpoint resource.
     *
     * @param username   Username of the SIP endpoint
     * @param password   password of the SIP profile
     * @param caller_id  caller id of the SIP profile
     * @param send_as    Value to send as
     * @param ciphers    list of ciphers while creating the SIP
     * @param codecs     list of codecs needed while creating the SIP
     * @param encryption Encryption of your
     * @return Sip
     */
    public SipResponse.Sip create(String username, String password, String caller_id, String send_as, List<String> ciphers, List<String> codecs, String encryption) throws SignalWireException {

        String url = this.spaceName + "/api/relay/rest/endpoints/sip";

        Map<String, Object> json = new HashMap<>();
        json.put("username", username);
        json.put("password", password);
        json.put("caller_id", caller_id);
        json.put("send_as", send_as);
        json.put("ciphers", ciphers);
        json.put("codecs", codecs);
        json.put("encryption", encryption);

        try {
            HttpResponse<JsonNode> client = HttpClient.postClient(url, this.projectId, this.apiToken, json);

            if (client.getStatus() >= 202 && client.getStatus() <= 204) {
                return gson.fromJson(client.getBody().toString(), SipResponse.Sip.class);
            } else {
                ApiException error = gson.fromJson(client.getBody().toString(), ApiException.class);
                throw new SignalWireException(error.getCode(), error.getMessage(), error.getMore_info(), error.getStatus());
            }

        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Retrieves the details of a SIP Endpoint that has been previously created.
     * Use the unique ID that was returned from your previous request to identify the specific SIP Endpoint.
     *
     * @param id unique ID of the SIP
     * @return Sip
     */
    public SipResponse.Sip get(String id) {

        String url = this.spaceName + "/api/relay/rest/endpoints/sip/" + id;

        try {
            HttpResponse<JsonNode> client = HttpClient.getClient(url, this.projectId, this.apiToken);
            if (client.getStatus() == 200) {
                return this.gson.fromJson(client.getBody().toString(), SipResponse.Sip.class);
            }
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Updates the specific SIP Endpoint by setting the values of any parameters passed in. Any parameters not provided will be unchanged.
     *
     * @param id         unique id of the SIP endpoint
     * @param username   Username of the SIP endpoint
     * @param password   password of the SIP profile
     * @param caller_id  caller id of the SIP profile
     * @param send_as    Value to send as
     * @param ciphers    list of ciphers while creating the SIP
     * @param codecs     list of codecs needed while creating the SIP
     * @param encryption Encryption of your
     * @return Sip
     */
    public SipResponse.Sip update(String id, String username, String password, String caller_id, String send_as, List<String> ciphers, List<String> codecs, String encryption) throws SignalWireException {

        try {
            String url = this.spaceName + "/api/relay/rest/endpoints/sip/" + id;

            Map<String, Object> json = new HashMap<>();
            json.put("username", username);
            json.put("password", password);
            json.put("caller_id", caller_id);
            json.put("send_as", send_as);
            json.put("ciphers", ciphers);
            json.put("codecs", codecs);
            json.put("encryption", encryption);

            HttpResponse<JsonNode> client = HttpClient.postClient(url, this.projectId, this.apiToken, json);

            if (client.getStatus() >= 200 || client.getStatus() <= 204) {
                return gson.fromJson(client.getBody().toString(), SipResponse.Sip.class);
            } else {
                ApiException error = gson.fromJson(client.getBody().toString(), ApiException.class);
                throw new SignalWireException(error.getCode(), error.getMessage(), error.getMore_info(), error.getStatus());
            }

        } catch (UnirestException exception) {
          throw new RuntimeException(exception);
        }
    }

    /**
     * Permanently deletes a SIP Endpoint. It cannot be undone.
     * Will reject any audio or video from currently registered devices and deregister any connections.
     *
     * @param id unique id of the SIP
     * @return SuccessResponse
     */
    public SuccessResponse delete(String id) {
        try {
            String url = this.spaceName + "/api/relay/rest/endpoints/sip/" + id;

            HttpResponse<JsonNode> request = HttpClient.deleteClient(url, this.projectId, this.apiToken);

            if (request.getStatus() == 204) {
                return new SuccessResponse(true);
            } else {
                return new SuccessResponse(false);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

    }

}
