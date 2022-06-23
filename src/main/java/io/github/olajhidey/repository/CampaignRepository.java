package io.github.olajhidey.repository;

import com.google.gson.Gson;

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

    public void brands(){}

    public void createBrand(){}

    public void getBrand(){}

    public void campaigns(){}

    public void createCampaign(){}

    public void getCampaign(){}

    public void updateCampaign(){}

    public void phoneAssignments(){}

    public void phoneAssignmentOrders(){}

    public void createPhoneAssignmentOrder(){}

    public void getPhoneAssignmentOrder(){}

    public void deletePhoneAssignment(){}
}
