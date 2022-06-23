package io.github.olajhidey.repository;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import io.github.olajhidey.endpoints.MessageInterface;
import io.github.olajhidey.exceptions.BadRequestException;
import io.github.olajhidey.http.RestClient;
import io.github.olajhidey.model.SuccessResponse;
import io.github.olajhidey.model.message.Message;
import io.github.olajhidey.model.message.Messages;
import io.github.olajhidey.model.message.NewMessageResponse;
import okhttp3.*;

import java.util.Objects;

public class MessageRepository implements MessageInterface {

    private String projectId;
    private String apiToken;
    private String spaceUrl;
    private RestClient client;
    private String baseUrl;
    private Gson gson;

    /**
     * @param projectID
     * @param apiToken
     * @param spaceUrl
     */
    public MessageRepository(String projectID, String apiToken, String spaceUrl) {
        this.projectId = projectID;
        this.apiToken = apiToken;
        this.spaceUrl = spaceUrl;
        this.client = new RestClient(projectID, apiToken);
        this.baseUrl = this.spaceUrl + "/api/laml/2010-04-01/Accounts/" + this.projectId + "/Messages";
        gson = new Gson();
    }

    /**
     * Get all the messages for the projectId provided
     *
     * @return Messages
     */
    @Override
    public Messages getMessages() {
        try {
            HttpResponse<JsonNode> request = Unirest.get(this.baseUrl)
                    .basicAuth(this.projectId, this.apiToken)
                    .header("Accept", "application/json")
                    .asJson();

            String response = request.getBody().toString();

            return gson.fromJson(response, Messages.class);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Get all messages using the following parameters
     *
     * @param dateSent
     * @param from
     * @param to
     * @param status
     * @param pageSize
     * @return
     */
    @Override
    public Messages getMessages(String dateSent, String from, String to, String status, String pageSize) {

        try {
            HttpUrl.Builder httpUrl = Objects.requireNonNull(HttpUrl.parse(baseUrl)).newBuilder()
                    .addQueryParameter("DateSent", dateSent)
                    .addQueryParameter("From", from)
                    .addQueryParameter("To", to)
                    .addQueryParameter("Status", status)
                    .addQueryParameter("PageSize", pageSize.isEmpty() ? "10" : pageSize);

            String url = httpUrl.build().toString();

            Request request = new Request.Builder().url(url).get().build();

            Response responseBody = client.getClient().newCall(request).execute();

            if (responseBody.code() >= 400)
                throw new BadRequestException(Objects.requireNonNull(responseBody.body()).string());
            else {
                String body = Objects.requireNonNull(responseBody.body()).string();
                return gson.fromJson(body, Messages.class);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * Create a new phone number with the below parameters
     *
     * @param to
     * @param from
     * @param body
     * @return
     */

    @Override
    public NewMessageResponse create(String to, String from, String body) {

        try {
            RequestBody bodyPost = new FormBody.Builder()
                    .add("To", to)
                    .add("From", from)
                    .add("Body", body)
                    .build();

            Request request = new Request.Builder()
                    .url(baseUrl)
                    .post(bodyPost)
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();

            String response = client.getClient().newCall(request).execute().body().string();

            return gson.fromJson(response, NewMessageResponse.class);

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Create a new message with the following params
     *
     * @param to
     * @param from
     * @param body
     * @param mediaUrl
     * @param statusCallback
     * @param validityPeriod
     * @return
     */
    @Override
    public NewMessageResponse create(String to, String from, String body, String mediaUrl, String statusCallback, String validityPeriod) {
        try {

            RequestBody formData = new FormBody.Builder()
                    .add("To", to)
                    .add("From", from)
                    .add("Body", body)
                    .add("StatusCallback", statusCallback)
                    .add("MediaUrl", mediaUrl)
                    .add("ValidityPeriod", String.valueOf(validityPeriod))
                    .build();

            Request request = new Request.Builder()
                    .url(baseUrl)
                    .post(formData)
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();

            String response = client.getClient().newCall(request).execute().body().string();

            return gson.fromJson(response, NewMessageResponse.class);

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Get the item of a particular message by SID
     *
     * @param sid
     * @return
     */

    @Override
    public Message get(String sid) {
        try {
            Request request = new Request.Builder()
                    .url(baseUrl + "/" + sid)
                    .get()
                    .addHeader("Accept", "application/json")
                    .build();
            String response = client.getClient().newCall(request).execute().body().string();
            return gson.fromJson(response, Message.class);

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Update a message by SID
     *
     * @param sid
     * @param body
     * @return
     */

    @Override
    public NewMessageResponse update(String sid, String body) {

        try {

            HttpUrl.Builder urlbuilder = HttpUrl.parse(baseUrl).newBuilder()
                    .addPathSegment(sid);

            String url = urlbuilder.build().toString();
            System.out.println(url);

            HttpResponse<JsonNode> response = Unirest.put(url)
                    .basicAuth(this.projectId, this.apiToken)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .body("{\"Body\": \"\"")
                    .asJson();

            String data = response.getBody().toString();
            System.out.println(data);

            return gson.fromJson(data, NewMessageResponse.class);

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }


    /**
     * Delete a message from a space by SID
     *
     * @param sid
     * @return
     */
    @Override
    public SuccessResponse delete(String sid) {

        try {

            HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder()
                    .addPathSegment(sid);

            String url = urlBuilder.build().toString();

            HttpResponse<JsonNode> request = Unirest.delete(url)
                    .basicAuth(this.projectId, this.apiToken)
                    .header("Accept", "application/json")
                    .body("null")
                    .asJson();

            System.out.println(request.getBody());

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
