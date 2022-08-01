package io.github.signalwirecommunity.forms;

import io.github.signalwirecommunity.enums.CompanyVertical;
import io.github.signalwirecommunity.enums.LegalEntity;

public class BrandCreate {
    public String name;
    public String company_name;
    public String company_email;
    public String contact_email;
    public String contact_phone;
    public String ein_issuing_country;
    public LegalEntity legal_entity_type;
    public String ein;
    public String company_address;
    public CompanyVertical companyVertical;
    public String company_website;
    public String csp_brand_reference;
    public String csp_self_registered;

    public BrandCreate(String name, String company_name, String company_email, String contact_email, String contact_phone, String ein_issuing_country, LegalEntity legal_entity_type, String ein, String company_address, CompanyVertical companyVertical, String company_website, String csp_brand_reference, String csp_self_registered) {
        this.name = name;
        this.company_name = company_name;
        this.company_email = company_email;
        this.contact_email = contact_email;
        this.contact_phone = contact_phone;
        this.ein_issuing_country = ein_issuing_country;
        this.legal_entity_type = legal_entity_type;
        this.ein = ein;
        this.company_address = company_address;
        this.companyVertical = companyVertical;
        this.company_website = company_website;
        this.csp_brand_reference = csp_brand_reference;
        this.csp_self_registered = csp_self_registered;
    }
}
