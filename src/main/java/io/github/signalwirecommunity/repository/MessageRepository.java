package io.github.signalwirecommunity.repository;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import io.github.signalwirecommunity.endpoints.MessageInterface;
import io.github.signalwirecommunity.http.RestClient;
import io.github.signalwirecommunity.model.SuccessResponse;
import io.github.signalwirecommunity.model.message.*;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MessageRepository implements MessageInterface {

    private final String projectId;
    private final String apiToken;
    private final RestClient client;
    private final String baseUrl;
    private final Gson gson;

    public MessageRepository(String projectID, String apiToken, String spaceUrl) {
        this.projectId = projectID;
        this.apiToken = apiToken;
        this.client = new RestClient(projectID, apiToken);
        this.baseUrl = spaceUrl + "/api/laml/2010-04-01/Accounts/" + this.projectId + "/Messages";
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
     * Create a new phone number with the below parameters
     *
     * @param to   Destination phone number (E164 format)
     * @param from Phone number sending the message (E164 format)
     * @param body body of the message
     * @return NewMessageResponse
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
     * @param data This is an object that holds necessary parameters to create a message
     * @return NewMessageResponse
     */
    @Override
    public NewMessageResponse create(MessageRequest data) {
        try {
            HttpResponse<JsonNode> client = Unirest.post(baseUrl)
                    .basicAuth(this.projectId, this.apiToken)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .fields(data.response)
                    .asJson();

            String response = client.getBody().toString();

            return gson.fromJson(response, NewMessageResponse.class);

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Get the item of a particular message by SID
     *
     * @param sid unique SID for the phone call
     * @return Message
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
     * @param sid  unique SID for the phone call
     * @param body body of the message been sent
     * @return NewMessageResponse
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
     * @param sid unique SID for the phone call
     * @return SuccessResponse
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

    /**
     * Filter messages based on parameters chosen
     * @param filter filter object from MessageFilter object
     * @return Messages
     */
    @Override
    public Messages getMessages(MessageFilter filter) {
        try {
            HttpResponse<JsonNode> request = Unirest.get(this.baseUrl)
                    .basicAuth(this.projectId, this.apiToken)
                    .header("Accept", "application/json")
                    .queryString(filter.getData())
                    .asJson();

            String response = request.getBody().toString();

            return gson.fromJson(response, Messages.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }


}
