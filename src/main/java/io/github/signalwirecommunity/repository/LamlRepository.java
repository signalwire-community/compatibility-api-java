package io.github.signalwirecommunity.repository;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import io.github.signalwirecommunity.http.constants.Constant;
import io.github.signalwirecommunity.endpoints.XMLInterface;
import io.github.signalwirecommunity.http.RestClient;
import io.github.signalwirecommunity.model.SuccessResponse;
import io.github.signalwirecommunity.model.laml.Bin;
import io.github.signalwirecommunity.model.laml.LamlResponse;
import okhttp3.*;

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

            Request request = new Request.Builder()
                    .url(this.baseUrl)
                    .get()
                    .addHeader(Constant.ACCEPT, Constant.HEADER)
                    .build();

            String response = this.client.getClient().newCall(request).execute().body().string();

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

            HttpUrl.Builder urlBuilder = HttpUrl.parse(this.baseUrl).newBuilder()
                    .addQueryParameter("Name", friendlyName);

            String url = urlBuilder.build().toString();

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader(Constant.ACCEPT, Constant.HEADER)
                    .build();

            String response = this.client.getClient().newCall(request).execute().body().string();

            return gson.fromJson(response, LamlResponse.class);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Create an XML bin
     */
    public Bin create(String name, String contents) {

        try {

            RequestBody formData = new FormBody.Builder()
                    .add("Name", name)
                    .add("Contents", contents)
                    .build();

            Request request = new Request.Builder()
                    .url(this.baseUrl)
                    .post(formData)
                    .addHeader(Constant.ACCEPT, Constant.HEADER)
                    .addHeader(Constant.CONTENT, Constant.FORM_TYPE)
                    .build();

            String response = this.client.getClient().newCall(request).execute().body().string();
            return gson.fromJson(response, Bin.class);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
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
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader(Constant.ACCEPT, Constant.HEADER)
                    .build();
            String response = this.client.getClient().newCall(request).execute().body().string();

            return gson.fromJson(response, Bin.class);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * Update an XML by ID
     *
     * @param sid unique SID for the bin
     * @param name friendly name of the created bin
     * @param contents full contents and xml values of the bin
     * @return Bin
     */
    @Override
    public Bin update(String sid, String name, String contents) {

        try {
            HttpUrl.Builder urlBuilder = HttpUrl.parse(this.baseUrl).newBuilder()
                    .addPathSegment(sid);
            String url = urlBuilder.build().toString();

            RequestBody formData = new FormBody.Builder()
                    .add("Name", name)
                    .add("Contents", contents)
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(formData)
                    .addHeader(Constant.ACCEPT, Constant.HEADER)
                    .addHeader(Constant.CONTENT, Constant.FORM_TYPE)
                    .build();

            String response = this.client.getClient().newCall(request).execute().body().string();

            return gson.fromJson(response, Bin.class);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
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
        }

        return null;
    }

}
