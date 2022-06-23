package io.github.olajhidey.model.rest;

import java.util.List;

public class LookUp {
    public String country_code_number;
    public String national_number;
    public boolean possible_number;
    public boolean valid_number;
    public String national_number_formatted;
    public String international_number_formatted;
    public String e164;
    public String location;
    public String country_code;
    public List<String> timezones;
    public String number_type;
    public Carrier carrier;
    public Cnam cnam;

    public LookUp(String country_code_number,
                  String national_number,
                  boolean possible_number,
                  boolean valid_number,
                  String national_number_formatted,
                  String international_number_formatted,
                  String e164,
                  String location,
                  String country_code,
                  List<String> timezones,
                  String number_type,
                  Carrier carrier,
                  Cnam cnam) {
        this.country_code_number = country_code_number;
        this.national_number = national_number;
        this.possible_number = possible_number;
        this.valid_number = valid_number;
        this.national_number_formatted = national_number_formatted;
        this.international_number_formatted = international_number_formatted;
        this.e164 = e164;
        this.location = location;
        this.country_code = country_code;
        this.timezones = timezones;
        this.number_type = number_type;
        this.carrier = carrier;
        this.cnam = cnam;
    }

    public static class Carrier {
        public String lrn;
        public String spid;
        public String ocn;
        public String lata;
        public String city;
        public String state;
        public String jurisdiction;
        public String lec;
        public String linetype;

        public Carrier(String lrn, String spid, String ocn, String lata, String city, String state, String jurisdiction, String lec, String linetype) {
            this.lrn = lrn;
            this.spid = spid;
            this.ocn = ocn;
            this.lata = lata;
            this.city = city;
            this.state = state;
            this.jurisdiction = jurisdiction;
            this.lec = lec;
            this.linetype = linetype;
        }
    }

    public static class Cnam {
        public String caller_id;

        public Cnam(String caller_id) {
            this.caller_id = caller_id;
        }
    }

}


