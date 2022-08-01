package io.github.signalwirecommunity.model.rest;

public class CampaignResponse {
    public Links links;
    public Campaign data;

    public CampaignResponse(Links links, Campaign data) {
        this.links = links;
        this.data = data;
    }

    public static class Campaign {
        public String id;
        public String name;
        public String state;
        public String sms_use_case;
        public String campaign_verify_token;
        public String description;
        public String sample1;
        public String sample2;
        public String sample3;
        public String sample4;
        public String sample5;
        public String dynamic_templates;
        public String opt_in_description;
        public String opt_out_description;
        public String number_pooling_per_campaign;
        public Boolean direct_lending;
        public Boolean embedded_link;
        public Boolean embedded_phone;
        public Boolean affiliate_marketing;
        public Boolean age_gated_content;
        public String csp_campaign_reference;
        public String created_at;
        public String updated_at;

        public Campaign(String id, String name, String state, String sms_use_case, String campaign_verify_token, String description, String sample1, String sample2, String sample3, String sample4, String sample5, String dynamic_templates, String opt_in_description, String opt_out_description, String number_pooling_per_campaign, Boolean direct_lending, Boolean embedded_link, Boolean embedded_phone, Boolean affiliate_marketing, Boolean age_gated_content, String csp_campaign_reference, String created_at, String updated_at) {
            this.id = id;
            this.name = name;
            this.state = state;
            this.sms_use_case = sms_use_case;
            this.campaign_verify_token = campaign_verify_token;
            this.description = description;
            this.sample1 = sample1;
            this.sample2 = sample2;
            this.sample3 = sample3;
            this.sample4 = sample4;
            this.sample5 = sample5;
            this.dynamic_templates = dynamic_templates;
            this.opt_in_description = opt_in_description;
            this.opt_out_description = opt_out_description;
            this.number_pooling_per_campaign = number_pooling_per_campaign;
            this.direct_lending = direct_lending;
            this.embedded_link = embedded_link;
            this.embedded_phone = embedded_phone;
            this.affiliate_marketing = affiliate_marketing;
            this.age_gated_content = age_gated_content;
            this.csp_campaign_reference = csp_campaign_reference;
            this.created_at = created_at;
            this.updated_at = updated_at;
        }
    }
}
