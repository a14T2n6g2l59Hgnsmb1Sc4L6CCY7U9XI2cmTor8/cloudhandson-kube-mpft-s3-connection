package dev.cloudhandson.mpft.s3.connection.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ErrorResponse {

    private String message;
    private String type;
    private Integer code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object errors;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, String type, Integer code, Object errors) {
        this.message = message;
        this.type = type;
        this.code = code;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getType() {
        return type;
    }

    public ErrorResponse setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public ErrorResponse setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Object getErrors() {
        return errors;
    }

    public ErrorResponse setErrors(Object errors) {
        this.errors = errors;
        return this;
    }
}

