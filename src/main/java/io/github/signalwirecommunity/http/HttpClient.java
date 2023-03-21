package io.github.signalwirecommunity.http;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.Map;

public class HttpClient {


    public static HttpResponse<JsonNode> postClient(String url, String projectId, String apiToken, String body) throws UnirestException {

        return Unirest.post(url)
                .basicAuth(projectId, apiToken)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("accept", "application/json")
                .body(body)
                .asJson();
    }

    public static HttpResponse<JsonNode> postClient(String url, String projectId, String apiToken, JSONObject body) throws UnirestException {

        return Unirest.post(url)
                .basicAuth(projectId, apiToken)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("accept", "application/json")
                .body(body)
                .asJson();
    }

    public static HttpResponse<JsonNode> postClient(String url, String projectId, String apiToken, Map<String, Object> fields) throws UnirestException {

        return Unirest.post(url)
                .basicAuth(projectId, apiToken)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("accept", "application/json")
                .fields(fields)
                .asJson();
    }


    public static HttpResponse<JsonNode> getClient(String url, String projectId, String apiToken) throws UnirestException{
        return Unirest.get(url)
                .basicAuth(projectId, apiToken)
                .header("Accept", "application/json")
                .asJson();
    }

    public static HttpResponse<JsonNode> getClient(String url, String projectId, String apiToken, Map<String, Object> queryString) throws UnirestException{
        return Unirest.get(url)
                .basicAuth(projectId, apiToken)
                .header("Accept", "application/json")
                .queryString(queryString)
                .asJson();
    }

    public static HttpResponse<JsonNode> deleteClient(String url, String projectId, String apiToken) throws UnirestException{
        return Unirest.delete(url)
                .header("Accept", "application/json")
                .basicAuth(projectId, apiToken)
                .asJson();
    }


}
