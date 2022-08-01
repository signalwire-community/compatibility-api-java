package io.github.signalwirecommunity.model.rest;

public class BrandResponse {

    public Links links;
    public Brand data;

    public BrandResponse(Links links, Brand data) {
        this.links = links;
        this.data = data;
    }

    public static class Brand {
        public String id;
        public String state;
        public String name;
        public String company_name;
        public String contact_phone;
        public String ein_issuing_country;
        public String legal_entity_type;
        public String ein;
        public String company_address;
        public String company_vertical;
        public String company_website;
        public String csp_brand_reference;
        public Boolean csp_self_registered;
        public String created_at;
        public String update_at;

        public Brand(String id, String state, String name, String company_name, String contact_phone, String ein_issuing_country, String legal_entity_type, String ein, String company_address, String company_vertical, String company_website, String csp_brand_reference, Boolean csp_self_registered, String created_at, String update_at) {
            this.id = id;
            this.state = state;
            this.name = name;
            this.company_name = company_name;
            this.contact_phone = contact_phone;
            this.ein_issuing_country = ein_issuing_country;
            this.legal_entity_type = legal_entity_type;
            this.ein = ein;
            this.company_address = company_address;
            this.company_vertical = company_vertical;
            this.company_website = company_website;
            this.csp_brand_reference = csp_brand_reference;
            this.csp_self_registered = csp_self_registered;
            this.created_at = created_at;
            this.update_at = update_at;
        }
    }

}
