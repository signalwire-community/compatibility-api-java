package io.github.olajhidey.http;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class RestClient {

    private String projectId;
    private String apiToken;
    private OkHttpClient client;


    public RestClient(final String projectId, final String apiToken) {
        this.projectId = projectId;
        this.apiToken = apiToken;
        client = new OkHttpClient.Builder()
                .followRedirects(false)
                .followSslRedirects(false)
                .authenticator(new Authenticator() {
                    @Nullable
                    @Override
                    public Request authenticate(@Nullable Route route, @NotNull Response response) throws IOException {
                        String credentials = Credentials.basic(projectId, apiToken);
                        return response.request().newBuilder().header("Authorization",credentials).build();
                    }
                }).build();
    }


    public OkHttpClient getClient() {
        return client;
    }


}
