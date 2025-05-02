package com.theinsideshine.exceptions;

import java.time.Instant;

public class ApiError {

    private Instant timestamp;
    private int status;
    private int internalCode;
    private String errorCode;
    private String technicalMessage;
    private String userMessage;
    private String service;
    private String path;

    public ApiError() {
        this.timestamp = Instant.now();
    }

    public ApiError(int status, ErrorCode errorCode, String service, String path) {
        this.timestamp = Instant.now();
        this.status = status;
        this.internalCode = errorCode.getInternalCode();
        this.errorCode = errorCode.name();
        this.technicalMessage = errorCode.getTechnicalMessage();
        this.userMessage = errorCode.getUserMessage();
        this.service = service;
        this.path = path;
    }

    // getters y setters
    public Instant getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public int getInternalCode() {
        return internalCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getTechnicalMessage() {
        return technicalMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getService() {
        return service;
    }

    public String getPath() {
        return path;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setInternalCode(int internalCode) {
        this.internalCode = internalCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setTechnicalMessage(String technicalMessage) {
        this.technicalMessage = technicalMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
