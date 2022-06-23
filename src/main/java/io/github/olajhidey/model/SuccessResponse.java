package io.github.olajhidey.model;

public class SuccessResponse {
    private Boolean success;
    private String code;
    private String message;
    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public SuccessResponse(Boolean success, String code, String message, int status) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public SuccessResponse(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
