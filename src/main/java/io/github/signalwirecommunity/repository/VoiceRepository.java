package io.github.signalwirecommunity.repository;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import io.github.signalwirecommunity.endpoints.VoiceInterface;
import io.github.signalwirecommunity.http.RestClient;
import io.github.signalwirecommunity.http.constants.Constant;
import io.github.signalwirecommunity.model.SuccessResponse;
import io.github.signalwirecommunity.model.call.Call;
import io.github.signalwirecommunity.model.call.CallFilter;
import io.github.signalwirecommunity.model.call.CallResponse;
import io.github.signalwirecommunity.model.call.VoiceBuilder;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.util.Map;

public class VoiceRepository implements VoiceInterface {

    private String projectId;
    private String apiToken;
    private String spaceUrl;
    private RestClient client;
    private String baseUrl;
    private Gson gson;

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
        try {
            Request request = new Request.Builder()
                    .url(baseUrl)
                    .get()
                    .build();

            String response = client.getClient().newCall(request).execute().body().string();
            return gson.fromJson(response, CallResponse.class);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
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

            HttpResponse<JsonNode> client = Unirest.get(baseUrl)
                    .basicAuth(this.projectId, this.apiToken)
                    .header("Accept", "application/json")
                    .queryString(filter.response)
                    .asJson();

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
    public Call create(VoiceBuilder builder) {

        String response;

        try {
            HttpResponse<JsonNode> request = Unirest.post(baseUrl)
                    .basicAuth(projectId, apiToken)
                    .header(Constant.ACCEPT, Constant.HEADER)
                    .header(Constant.CONTENT, Constant.FORM_TYPE)
                    .fields(builder.getResponse())
                    .asJson();

            int status = request.getStatus();

            response = request.getBody().toString();

            if (status >= 200 && status <= 204) {
                return gson.fromJson(response, Call.class);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        return null;
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

            HttpResponse<JsonNode> response = Unirest.get(url)
                    .header("Accept", "application/json")
                    .basicAuth(this.projectId, this.apiToken)
                    .asJson();

            String body = response.getBody().toString();

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
    public Call update(String sid, VoiceBuilder callInfo) {

        String response;

        try {

            String url = baseUrl + "/" + sid;

            HttpResponse<JsonNode> request = Unirest.post(url)
                    .basicAuth(projectId, apiToken)
                    .header(Constant.CONTENT, Constant.FORM_TYPE)
                    .header(Constant.ACCEPT, Constant.HEADER)
                    .fields(callInfo.getResponse())
                    .asJson();

            int status = request.getStatus();

            response = request.getBody().toString();

            if (status >= 200 && status <= 204) {
                return gson.fromJson(response, Call.class);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
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
                return new SuccessResponse(false);
            }

        } catch (Exception exception) {
            exception.printStackTrace();

            return null;
        }
    }
}
