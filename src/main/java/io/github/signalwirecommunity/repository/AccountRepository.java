package io.github.signalwirecommunity.repository;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import io.github.signalwirecommunity.endpoints.AccountInterface;
import io.github.signalwirecommunity.model.account.Account;
import io.github.signalwirecommunity.model.account.AccountResponse;
import okhttp3.HttpUrl;

public class AccountRepository implements AccountInterface {

    private String projectId;
    private String apiToken;
    private String spaceUrl;
    private String baseUrl;
    private Gson gson;

    public AccountRepository(String projectId, String apiToken, String spaceUrl) {
        this.projectId = projectId;
        this.apiToken = apiToken;
        this.spaceUrl = spaceUrl;
        this.gson = new Gson();
        this.baseUrl = this.spaceUrl + "/api/laml/2010-04-01/Accounts";
    }


    /**
     * Get all accounts using the project ID
     *
     * @return
     */
    @Override
    public AccountResponse getAccounts() {
        try {
            HttpResponse<JsonNode> request = Unirest.get(this.baseUrl)
                    .basicAuth(this.projectId, this.apiToken)
                    .header("Accept", "application/json")
                    .asJson();

            String response = request.getBody().toString();

            return gson.fromJson(response, AccountResponse.class);

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
    /**
     * Get the Account information by friendlyName
     *
     * @param friendlyName
     * @return
     */
    @Override
    public AccountResponse getAccountByName(String friendlyName) {
        try {

            HttpUrl.Builder httpUrl = HttpUrl.parse(baseUrl).newBuilder()
                    .addQueryParameter("FriendlyName", friendlyName);

            String url = httpUrl.build().toString();

            HttpResponse<JsonNode> request = Unirest.get(url)
                    .basicAuth(this.projectId, this.apiToken)
                    .header("Accept", "application/json")
                    .asJson();

            String response = request.getBody().toString();

            return gson.fromJson(response, AccountResponse.class);

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    /**
     * Get an account information using the SID
     *
     * @param sid
     * @return
     */
    @Override
    public Account getAccountBySid(String sid) {
        try {
            HttpUrl.Builder httpUrl = HttpUrl.parse(baseUrl).newBuilder()
                    .addPathSegment(sid);

            String url = httpUrl.build().toString();

            HttpResponse<JsonNode> request = Unirest.get(url)
                    .basicAuth(this.projectId, this.apiToken)
                    .header("Accept", "application/json")
                    .asJson();
            String response = request.getBody().toString();

            return gson.fromJson(response, Account.class);

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

    }
}
