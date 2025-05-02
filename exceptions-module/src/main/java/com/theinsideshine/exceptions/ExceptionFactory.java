package com.theinsideshine.exceptions;

public class ExceptionFactory {

    private ExceptionFactory() {
    }

    public static BadRequestException badRequest(ErrorCode errorCode, String service, String path) {
        return new BadRequestException(errorCode, service, path);
    }

    public static ResourceNotFoundException resourceNotFound(ErrorCode errorCode, String service, String path) {
        return new ResourceNotFoundException(errorCode, service, path);
    }

    public static EcheqNotFoundException echeqNotFound(ErrorCode errorCode, String service, String path) {
        return new EcheqNotFoundException(errorCode, service, path);
    }

    public static CommonException genericError(ErrorCode errorCode, String service, String path) {
        return new CommonException(errorCode, service, path) {
        };
    }
}