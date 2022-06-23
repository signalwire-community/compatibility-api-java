package io.github.olajhidey.model.call;

public class Call {
    private String account_sid;
    private String annotation;
    private String answered_by;
    private String api_version;
    private String caller_name;
    private String date_created;
    private String date_updated;
    private String direction;
    private int duration;
    private String end_time;
    private String forwarded_from;
    private String from;
    private String formatted_from;
    private String parent_call_sid;
    private String phone_number_sid;
    private Double price;
    private String price_unit;
    private String sid;
    private String start_time;
    private String status;
    private SubResource subresource_uris;
    private String to;
    private String formatted_to;
    private String uri;

    public Call(String account_sid, String annotation, String answered_by, String api_version, String caller_name, String date_created, String date_updated, String direction, int duration, String end_time, String forwarded_from, String from, String formatted_from, String parent_call_sid, String phone_number_sid, Double price, String price_unit, String sid, String start_time, String status, SubResource subresource_uris, String to, String formatted_to, String uri) {
        this.account_sid = account_sid;
        this.annotation = annotation;
        this.answered_by = answered_by;
        this.api_version = api_version;
        this.caller_name = caller_name;
        this.date_created = date_created;
        this.date_updated = date_updated;
        this.direction = direction;
        this.duration = duration;
        this.end_time = end_time;
        this.forwarded_from = forwarded_from;
        this.from = from;
        this.formatted_from = formatted_from;
        this.parent_call_sid = parent_call_sid;
        this.phone_number_sid = phone_number_sid;
        this.price = price;
        this.price_unit = price_unit;
        this.sid = sid;
        this.start_time = start_time;
        this.status = status;
        this.subresource_uris = subresource_uris;
        this.to = to;
        this.formatted_to = formatted_to;
        this.uri = uri;
    }

    public String getAccount_sid() {
        return account_sid;
    }

    public void setAccount_sid(String account_sid) {
        this.account_sid = account_sid;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getAnswered_by() {
        return answered_by;
    }

    public void setAnswered_by(String answered_by) {
        this.answered_by = answered_by;
    }

    public String getApi_version() {
        return api_version;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    public String getCaller_name() {
        return caller_name;
    }

    public void setCaller_name(String caller_name) {
        this.caller_name = caller_name;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(String date_updated) {
        this.date_updated = date_updated;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getForwarded_from() {
        return forwarded_from;
    }

    public void setForwarded_from(String forwarded_from) {
        this.forwarded_from = forwarded_from;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFormatted_from() {
        return formatted_from;
    }

    public void setFormatted_from(String formatted_from) {
        this.formatted_from = formatted_from;
    }

    public String getParent_call_sid() {
        return parent_call_sid;
    }

    public void setParent_call_sid(String parent_call_sid) {
        this.parent_call_sid = parent_call_sid;
    }

    public String getPhone_number_sid() {
        return phone_number_sid;
    }

    public void setPhone_number_sid(String phone_number_sid) {
        this.phone_number_sid = phone_number_sid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPrice_unit() {
        return price_unit;
    }

    public void setPrice_unit(String price_unit) {
        this.price_unit = price_unit;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SubResource getSubresource_uris() {
        return subresource_uris;
    }

    public void setSubresource_uris(SubResource subresource_uris) {
        this.subresource_uris = subresource_uris;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFormatted_to() {
        return formatted_to;
    }

    public void setFormatted_to(String formatted_to) {
        this.formatted_to = formatted_to;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
