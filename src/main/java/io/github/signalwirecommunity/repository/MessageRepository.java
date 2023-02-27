package io.github.signalwirecommunity.repository;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.github.signalwirecommunity.endpoints.MessageInterface;
import io.github.signalwirecommunity.exceptions.ApiException;
import io.github.signalwirecommunity.exceptions.SignalWireException;
import io.github.signalwirecommunity.http.HttpClient;
import io.github.signalwirecommunity.http.RestClient;
import io.github.signalwirecommunity.model.SuccessResponse;
import io.github.signalwirecommunity.model.message.*;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
            HttpResponse<JsonNode> request = HttpClient.getClient(this.baseUrl, this.projectId, this.apiToken);

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
    public NewMessageResponse create(String to, String from, String body) throws SignalWireException{

        try {
            Map<String, Object> formData = new HashMap<>();
                    formData.put("To", to);
                    formData.put("From", from);
                    formData.put("Body", body);

            HttpResponse<JsonNode> client = HttpClient.postClient(this.baseUrl, this.projectId, this.apiToken, formData);

            String response = client.getBody().toString();

            if (client.getStatus() >= 200 && client.getStatus() <= 204) {
                return gson.fromJson(response, NewMessageResponse.class);
            }else{
                ApiException error = gson.fromJson(response, ApiException.class);
                throw new SignalWireException(error.getCode(), error.getMessage(), error.getMore_info(), error.getStatus());
            }

        } catch (UnirestException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Create a new message with the following params
     *
     * @param data This is an object that holds necessary parameters to create a message
     * @return NewMessageResponse
     */
    @Override
    public NewMessageResponse create(MessageRequest data) throws SignalWireException {
        try {
            HttpResponse<JsonNode> client = HttpClient.postClient(this.baseUrl, this.projectId, this.apiToken, data.response);

            String response = client.getBody().toString();

            if (client.getStatus() >= 200 && client.getStatus() <= 204) {
                return gson.fromJson(response, NewMessageResponse.class);
            }else{
                ApiException error = gson.fromJson(response, ApiException.class);
                throw new SignalWireException(error.getCode(), error.getMessage(), error.getMore_info(), error.getStatus());
            }

        } catch (UnirestException exception) {
            throw new RuntimeException(exception);
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
            String url = baseUrl + "/" + sid;
            HttpResponse<JsonNode> client = HttpClient.getClient(url, this.projectId, this.apiToken);
            String response = client.getBody().toString();
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
    public NewMessageResponse update(String sid, String body) throws SignalWireException{

        try {
            HttpUrl.Builder urlbuilder = HttpUrl.parse(baseUrl).newBuilder()
                    .addPathSegment(sid);

            String url = urlbuilder.build().toString();
            Map<String, Object> form = new HashMap<>();
            form.put("Body", "");

            HttpResponse<JsonNode> client = HttpClient.postClient(url, this.projectId, this.apiToken, form);

            String data = client.getBody().toString();

            if (client.getStatus() >= 200 && client.getStatus() <= 204) {
                return gson.fromJson(data, NewMessageResponse.class);
            }else{
                ApiException error = gson.fromJson(data, ApiException.class);
                throw new SignalWireException(error.getCode(), error.getMessage(), error.getMore_info(), error.getStatus());
            }

        } catch (UnirestException exception) {
            throw new RuntimeException(exception);
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

    /**
     * Filter messages based on parameters chosen
     * @param filter filter object from MessageFilter object
     * @return Messages
     */
    @Override
    public Messages getMessages(MessageFilter filter) {
        try {
            HttpResponse<JsonNode> request = HttpClient.getClient(this.baseUrl, this.projectId, this.apiToken, filter.getData());

            String response = request.getBody().toString();

            return gson.fromJson(response, Messages.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }


}
