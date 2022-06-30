package io.github.signalwirecommunity.model.phone;

public class Capabilities {
    private Boolean voice;
    private Boolean sms;
    private Boolean mms;
    private Boolean fax;

    public Capabilities(Boolean voice,
                        Boolean sms,
                        Boolean mms,
                        Boolean fax) {
        this.voice = voice;
        this.sms = sms;
        this.mms = mms;
        this.fax = fax;
    }

    public Boolean getVoice() {
        return voice;
    }

    public void setVoice(Boolean voice) {
        this.voice = voice;
    }

    public Boolean getSms() {
        return sms;
    }

    public void setSms(Boolean sms) {
        this.sms = sms;
    }

    public Boolean getMms() {
        return mms;
    }

    public void setMms(Boolean mms) {
        this.mms = mms;
    }

    public Boolean getFax() {
        return fax;
    }

    public void setFax(Boolean fax) {
        this.fax = fax;
    }
}
