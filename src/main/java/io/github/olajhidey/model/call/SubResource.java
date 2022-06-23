package io.github.olajhidey.model.call;

public class SubResource {

    private String notifications;
    private String recordings;

    public SubResource(String notifications, String recordings) {
        this.notifications = notifications;
        this.recordings = recordings;
    }

    public String getNotifications() {
        return notifications;
    }

    public void setNotifications(String notifications) {
        this.notifications = notifications;
    }

    public String getRecordings() {
        return recordings;
    }

    public void setRecordings(String recordings) {
        this.recordings = recordings;
    }
}
