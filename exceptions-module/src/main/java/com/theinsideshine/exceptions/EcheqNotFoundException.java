package com.theinsideshine.exceptions;

public class EcheqNotFoundException extends CommonException {


    public EcheqNotFoundException(ErrorCode errorCode, String service, String path) {
        super(errorCode, service, path);
    }
}
