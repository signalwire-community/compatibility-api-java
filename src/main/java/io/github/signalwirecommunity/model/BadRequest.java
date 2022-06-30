package io.github.signalwirecommunity.model;

public class BadRequest {

    private String code;
    private String message;
    private String more_info;
    private String status;

    public BadRequest(String code, String message, String more_info, String status){
        this.code=code;
        this.message=message;
        this.more_info=more_info;
        this.status=status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMore_info() {
        return more_info;
    }

    public void setMore_info(String more_info) {
        this.more_info = more_info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
