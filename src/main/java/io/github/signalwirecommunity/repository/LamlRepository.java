package io.github.signalwirecommunity.repository;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.github.signalwirecommunity.exceptions.ApiException;
import io.github.signalwirecommunity.exceptions.SignalWireException;
import io.github.signalwirecommunity.http.HttpClient;
import io.github.signalwirecommunity.http.constants.Constant;
import io.github.signalwirecommunity.endpoints.XMLInterface;
import io.github.signalwirecommunity.http.RestClient;
import io.github.signalwirecommunity.model.SuccessResponse;
import io.github.signalwirecommunity.model.laml.Bin;
import io.github.signalwirecommunity.model.laml.LamlResponse;
import okhttp3.*;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LamlRepository implements XMLInterface {

    private String projectId;
    private String apiToken;
    private String spaceUrl;
    private String baseUrl;
    private RestClient client;
    private Gson gson;

    public LamlRepository(String projectId, String apiToken, String spaceUrl) {
        this.projectId = projectId;
        this.apiToken = apiToken;
        this.spaceUrl = spaceUrl;
        this.client = new RestClient(projectId, apiToken);
        this.baseUrl = this.spaceUrl + "/api/laml/2010-04-01/Accounts/" + projectId + "/LamlBins";
        this.gson = new Gson();
    }

    /**
     * Returns a list of your XML Bins.
     * The Bins are returned sorted by creation date, with the most recent appearing first
     *
     * @return LamlResponse
     */
    @Override
    public LamlResponse list() {

        try {

            HttpResponse<JsonNode> client = HttpClient.getClient(this.baseUrl, this.projectId, this.apiToken);

            String response = client.getBody().toString();

            return gson.fromJson(response, LamlResponse.class);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * Get list of xml bins by friendly name
     *
     * @param friendlyName name of xml to be queried
     * @return LamlResponse
     */
    @Override
    public LamlResponse list(String friendlyName) {

        try {

            Map<String, Object> params = new HashMap<>();
            params.put("Name", friendlyName);

            HttpResponse<JsonNode> client = HttpClient.getClient(this.baseUrl, this.projectId, this.apiToken, params);

            String response = client.getBody().toString();

            return gson.fromJson(response, LamlResponse.class);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Create an XML bin
     */
    public Bin create(String name, String contents) throws SignalWireException {

        try {
            Map<String, Object> formData = new HashMap<>();
            formData.put("Name", name);
            formData.put("Contents", contents);

            HttpResponse<JsonNode> request = HttpClient.postClient(baseUrl, this.projectId, this.apiToken, formData);

            String response = request.getBody().toString();

            if (request.getStatus() >= 200 && request.getStatus() <= 204) {
                return gson.fromJson(response, Bin.class);
            } else {
                ApiException error = gson.fromJson(response, ApiException.class);
                throw new SignalWireException(error.getCode(), error.getMessage(), error.getMore_info(), error.getStatus());
            }

        } catch (UnirestException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Retrieve a particular xml bin by ID
     *
     * @return Bin
     */
    public Bin get(String sid) {
        try {

            HttpUrl.Builder urlBuilder = HttpUrl.parse(this.baseUrl).newBuilder()
                    .addPathSegment(sid);
            String url = urlBuilder.build().toString();
            HttpResponse<JsonNode> client = HttpClient.getClient(url, this.projectId, this.apiToken);
            String response = client.getBody().toString();
            return gson.fromJson(response, Bin.class);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * Update an XML by ID
     *
     * @param sid      unique SID for the bin
     * @param name     friendly name of the created bin
     * @param contents full contents and xml values of the bin
     * @return Bin
     */
    @Override
    public Bin update(String sid, String name, String contents) throws SignalWireException {

        try {
            Map<String, Object> formData = new HashMap<>();
            formData.put("Name", name);
            formData.put("Contents", contents);

            HttpResponse<JsonNode> request = HttpClient.postClient(baseUrl, this.projectId, this.apiToken, formData);

            String response = request.getBody().toString();

            if (request.getStatus() >= 200 && request.getStatus() <= 204) {
                return gson.fromJson(response, Bin.class);
            } else {
                ApiException error = gson.fromJson(response, ApiException.class);
                throw new SignalWireException(error.getCode(), error.getMessage(), error.getMore_info(), error.getStatus());
            }

        } catch (UnirestException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Delete an XML bin by ID
     *
     * @param sid unique SID for the bin
     * @return SuccessResponse
     */
    @Override
    public SuccessResponse delete(String sid) {

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
        }
        return null;
    }

}
