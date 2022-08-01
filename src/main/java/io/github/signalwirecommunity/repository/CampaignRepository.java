package io.github.signalwirecommunity.repository;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import io.github.signalwirecommunity.forms.BrandCreate;
import io.github.signalwirecommunity.http.constants.Constant;
import io.github.signalwirecommunity.model.rest.BrandResponse;
import io.github.signalwirecommunity.model.rest.CampaignResponse;

public class CampaignRepository {
    private final String projectId;
    private final String apiToken;
    private final String spaceName;
    private final Gson gson;

    public CampaignRepository(String projectId, String apiToken, String spaceName) {
        this.projectId = projectId;
        this.apiToken = apiToken;
        this.spaceName = spaceName;
        this.gson = new Gson();
    }

    public BrandResponse brands() {
        try {
            String url = this.spaceName + "/api/relay/rest/registry/beta/brands";

            HttpResponse<JsonNode> request = Unirest.get(url)
                    .basicAuth(this.projectId, this.apiToken)
                    .header(Constant.ACCEPT, Constant.HEADER)
                    .asJson();

            if (request.getStatus() >= 200 && request.getStatus() <= 204) {
                String response = request.getBody().toString();
                return gson.fromJson(response, BrandResponse.class);
            } else {
                String response = request.getBody().toString();
                System.out.println(response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public BrandResponse.Brand createBrand(BrandCreate data) {
        try {
            String url = this.spaceName + "/api/relay/rest/registry/beta/brands";
            String json = gson.toJson(data);

            HttpResponse<JsonNode> request = Unirest.post(url)
                    .basicAuth(this.projectId, this.apiToken)
                    .header(Constant.ACCEPT, Constant.HEADER)
                    .header(Constant.CONTENT, Constant.FORM_TYPE)
                    .body(json)
                    .asJson();

            if (request.getStatus() == 201) {
                String response = request.getBody().toString();
                return gson.fromJson(response, BrandResponse.Brand.class);
            } else {
                String response = request.getBody().toString();
                System.out.println(response);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println(exception.getMessage());
        }
        return null;
    }

    /**
     * Retrieve a particular brand from a user space
     *
     * @param id ID of the particular brand to retrieve
     * @return BrandResponse.Brand
     */
    public BrandResponse.Brand getBrand(String id) {
        try {
            String url = this.spaceName + "/api/relay/rest/registry/beta/brands/" + id;

            HttpResponse<JsonNode> request = Unirest.get(url)
                    .basicAuth(projectId, apiToken)
                    .header(Constant.ACCEPT, Constant.HEADER)
                    .asJson();

            if (request.getStatus() >= 200 && request.getStatus() <= 204) {
                String response = request.getBody().toString();
                return gson.fromJson(response, BrandResponse.Brand.class);
            } else {
                String response = request.getBody().toString();
                System.out.println(response);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public CampaignResponse campaigns(String brandId) {
        try {
            String url = this.spaceName + "/api/relay/rest/registry/beta/brands/" + brandId + "campaigns";

            HttpResponse<JsonNode> request = Unirest.get(url)
                    .basicAuth(projectId , apiToken)
                    .header(Constant.ACCEPT, Constant.HEADER)
                    .asJson();

            int status = request.getStatus();
            String response = request.getBody().toString();

            if (status == 200) {
                return gson.fromJson(response, CampaignResponse.class);
            } else {
                System.out.println(response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void createCampaign() {
    }

    public void getCampaign() {
    }

    public void updateCampaign() {
    }

    public void phoneAssignments() {
    }

    public void phoneAssignmentOrders() {
    }

    public void createPhoneAssignmentOrder() {
    }

    public void getPhoneAssignmentOrder() {
    }

    public void deletePhoneAssignment() {
    }
}
