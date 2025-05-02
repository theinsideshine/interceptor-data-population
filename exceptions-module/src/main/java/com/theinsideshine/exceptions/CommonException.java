// ---- exceptions/CommonException.java ----
package com.theinsideshine.exceptions;

public abstract class CommonException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String service;
    private final String path;

    public CommonException(ErrorCode errorCode, String service, String path) {
        super(errorCode.getTechnicalMessage());
        this.errorCode = errorCode;
        this.service = service;
        this.path = path;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getService() {
        return service;
    }

    public String getPath() {
        return path;
    }
}