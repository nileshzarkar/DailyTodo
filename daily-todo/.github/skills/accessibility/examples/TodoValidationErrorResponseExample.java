package org.dailydots.examples.accessibility;

import java.util.ArrayList;
import java.util.List;

/**
 * Example DTO for field-level validation errors that can be mapped to
 * aria-describedby and aria-invalid states in the UI.
 */
public class TodoValidationErrorResponseExample {

    private String message;
    private List<FieldError> fieldErrors = new ArrayList<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public void addFieldError(String field, String errorMessage) {
        this.fieldErrors.add(new FieldError(field, errorMessage));
    }

    /**
     * Represents one invalid field from the request payload.
     */
    public static class FieldError {

        private String field;
        private String message;

        public FieldError() {
        }

        public FieldError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
