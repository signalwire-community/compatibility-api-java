package io.github.signalwirecommunity.repository;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.github.signalwirecommunity.endpoints.VoiceInterface;
import io.github.signalwirecommunity.exceptions.ApiException;
import io.github.signalwirecommunity.exceptions.SignalWireException;
import io.github.signalwirecommunity.http.HttpClient;
import io.github.signalwirecommunity.http.RestClient;
import io.github.signalwirecommunity.http.constants.Constant;
import io.github.signalwirecommunity.model.SuccessResponse;
import io.github.signalwirecommunity.model.call.Call;
import io.github.signalwirecommunity.model.call.CallFilter;
import io.github.signalwirecommunity.model.call.CallResponse;
import io.github.signalwirecommunity.model.call.VoiceBuilder;
import okhttp3.HttpUrl;

import java.util.Objects;

public class VoiceRepository implements VoiceInterface {

    private final String projectId;
    private final String apiToken;
    private String spaceUrl;
    private final RestClient client;
    private final String baseUrl;
    private final Gson gson;

    public VoiceRepository(String projectId, String apiToken, String spaceUrl) {
        this.projectId = projectId;
        this.apiToken = apiToken;
        this.spaceUrl = spaceUrl;
        gson = new Gson();
        client = new RestClient(projectId, apiToken);
        this.baseUrl = this.spaceUrl + "/api/laml/2010-04-01/Accounts/" + this.projectId + "/Calls";

    }

    /**
     * Get all the voice calls in a particular project
     */
    @Override
    public CallResponse calls() {

        HttpResponse<JsonNode> client = null;

        try {
            client = HttpClient.getClient(baseUrl, this.projectId, this.apiToken);
            String response = client.getBody().toString();

            return gson.fromJson(response, CallResponse.class);
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get all the calls using the below parameters
     *
     * @param filter Holds the parameter needed to query list of calls
     * @return CallResponse
     */
    @Override
    public CallResponse calls(CallFilter filter) {

        try {
            HttpResponse<JsonNode> client = HttpClient.getClient(baseUrl, projectId, apiToken, filter.response);
            String response = client.getBody().toString();
            return gson.fromJson(response, CallResponse.class);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Create a call using the below and following parameters
     *
     * @return Call
     */
    @Override
    public Call create(VoiceBuilder builder) throws SignalWireException {

        String response;
        try {

            HttpResponse<JsonNode> client = HttpClient.postClient(baseUrl, projectId, apiToken, builder.getResponse());
            int status = client.getStatus();
            response = client.getBody().toString();

            if (status >= 200 && status <= 204) {
                return gson.fromJson(response, Call.class);
            } else {
                ApiException error = gson.fromJson(response, ApiException.class);
                throw new SignalWireException(error.getCode(), error.getMessage(), error.getMore_info(), error.getStatus());
            }
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get information of a Call by using SID
     *
     * @param sid unique id of the call
     * @return Call
     */
    @Override
    public Call get(String sid) {

        try {

            HttpUrl.Builder builder = HttpUrl.parse(baseUrl).newBuilder()
                    .addPathSegment(sid);

            String url = builder.build().toString();

            HttpResponse<JsonNode> client = HttpClient.getClient(url, projectId, apiToken);

            String body = client.getBody().toString();

            return gson.fromJson(body, io.github.signalwirecommunity.model.call.Call.class);

        } catch (Exception exception) {
            exception.printStackTrace();

            return null;
        }
    }

    /**
     * Update the item of a call using the SID
     *
     * @param sid      unique SID of the phone call
     * @param callInfo All call information from VoiceBuilder
     * @return Call
     */
    @Override
    public Call update(String sid, VoiceBuilder callInfo) throws SignalWireException {

        String response;

        try {
            String url = baseUrl + "/" + sid;
            HttpResponse<JsonNode> client = HttpClient.postClient(url, projectId, apiToken, callInfo.getResponse());
            int status = client.getStatus();
            response = client.getBody().toString();

            if (status >= 200 && status <= 204) {
                return gson.fromJson(response, Call.class);
            } else {
                ApiException error = gson.fromJson(response, ApiException.class);
                throw new SignalWireException(error.getCode(), error.getMessage(), error.getMore_info(), error.getStatus());
            }
        }catch (UnirestException unirestException){
            throw new RuntimeException(unirestException);
        }
    }

    /**
     * Delete a call using the following parameter
     *
     * @param sid unique SID of the call
     * @return SuccessResponse
     */
    @Override
    public SuccessResponse delete(String sid) {
        try {

            HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl)).newBuilder()
                    .addPathSegment(sid);

            String url = urlBuilder.build().toString();

            HttpResponse<JsonNode> client = HttpClient.deleteClient(url, projectId, apiToken);

            if (client.getStatus() == 204) {
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
