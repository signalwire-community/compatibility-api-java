package io.github.olajhidey.exceptions;

import java.util.List;

public class LookupException extends Exception {

    public LookUpFailed error;

    public LookupException(LookUpFailed error) {
        this.error = error;
    }

    public LookUpFailed getError() {
        return error;
    }

    public static class LookUpFailed {
        public List<LookupError> errors;

        public LookUpFailed(List<LookupError> errors) {
            this.errors = errors;
        }
    }

    public static class LookupError {
        public String code;
        public String detail;
        public String title;
        public String status;

        public LookupError(String code, String detail, String title, String status) {
            this.code = code;
            this.detail = detail;
            this.title = title;
            this.status = status;
        }
    }
}
