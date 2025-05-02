package com.theinsideshine.exceptions;

public class BadRequestException extends CommonException {

    public BadRequestException(ErrorCode errorCode, String service, String path) {
        super(errorCode, service, path);
    }
}