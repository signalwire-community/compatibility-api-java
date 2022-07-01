package io.github.signalwirecommunity.repository;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import io.github.signalwirecommunity.endpoints.VoiceInterface;
import io.github.signalwirecommunity.http.RestClient;
import io.github.signalwirecommunity.model.SuccessResponse;
import io.github.signalwirecommunity.model.call.Call;
import io.github.signalwirecommunity.model.call.CallResponse;
import io.github.signalwirecommunity.model.message.Messages;
import okhttp3.*;

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
     * @param endTime         end time
     * @param endTimeBefore   end time before
     * @param endTimeAfter    end time after
     * @param from            phone number sending the call
     * @param parentCallSid   parent call Sid
     * @param startTime       start time
     * @param startTimeBefore start time before
     * @param startTimeAfter  start time after
     * @param status          status of the message
     * @param to              destination phone call
     * @return CallResponse
     */
    @Override
    public CallResponse calls(String endTime,
                              String endTimeBefore,
                              String endTimeAfter,
                              String from,
                              String parentCallSid,
                              String startTime,
                              String startTimeBefore,
                              String startTimeAfter,
                              String status,
                              String to) {

        try {
            HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder()
                    .addQueryParameter("From", from)
                    .addQueryParameter("To", to)
                    .addQueryParameter("EndTime", endTime)
                    .addQueryParameter("EndTime<", endTimeBefore)
                    .addQueryParameter("EndTime>", endTimeAfter)
                    .addQueryParameter("StartTime", startTime)
                    .addQueryParameter("StartTime<", startTimeBefore)
                    .addQueryParameter("StartTime>", startTimeAfter)
                    .addQueryParameter("ParentCallSid", parentCallSid)
                    .addQueryParameter("Status", status);

            String url = urlBuilder.build().toString();

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("Accept", "application/json")
                    .build();

            String response = client.getClient().newCall(request).execute().body().string();

            return gson.fromJson(response, CallResponse.class);

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

    }


    /**
     * Create a call using the belowing and following parameters
     *
     * @param from           phone number sending the call
     * @param to             destination phone call
     * @param url            url to bin of the phone call
     * @param record         ask to record the phone call
     * @param statusCallback link to status of the phone call
     * @return Call
     */
    @Override
    public Call create(String from,
                       String to,
                       String url,
                       Boolean record,
                       String statusCallback) {

        try {

            RequestBody formData = new FormBody.Builder()
                    .add("From", from)
                    .add("To", to)
                    .add("Url", url)
                    .add("Record", record.toString())
                    .add("StatusCallback", statusCallback).build();


            Request request = new Request.Builder()
                    .url(baseUrl)
                    .post(formData)
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();

            String response = client.getClient().newCall(request).execute().body().string();

            return gson.fromJson(response, io.github.signalwirecommunity.model.call.Call.class);

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
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
     * @param sid unique SID of the phone call
     * @param url url of the call xml bin
     * @param fallbackUrl fallback url if the call failed
     * @param status status of the call
     * @param statusCallBack link to get information on the status of your call
     * @return Call
     */
    @Override
    public Call update(String sid, String url, String fallbackUrl, String status, String statusCallBack) {

        try {
            RequestBody formData = new FormBody.Builder()
                    .add("Url", url)
                    .add("FallbackUrl", fallbackUrl)
                    .add("Status", status)
                    .add("StatusCallback", statusCallBack)
                    .build();

            Request request = new Request.Builder()
                    .url(baseUrl + "/" + sid)
                    .put(formData)
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();


            String response = client.getClient().newCall(request).execute().body().toString();

            return gson.fromJson(response, Call.class);

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
