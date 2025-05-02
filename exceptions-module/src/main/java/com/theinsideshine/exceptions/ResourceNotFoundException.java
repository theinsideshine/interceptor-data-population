package com.theinsideshine.exceptions;

public class ResourceNotFoundException extends CommonException {

    public ResourceNotFoundException(ErrorCode errorCode, String service, String path) {
        super(errorCode, service, path);
    }
}