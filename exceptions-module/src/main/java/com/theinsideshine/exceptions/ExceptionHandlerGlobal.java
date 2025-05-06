package com.theinsideshine.exceptions;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ExceptionHandlerGlobal {

    private static final String SERVICE_NAME = "Servicio1";

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ApiError> handleCommonException(CommonException ex) {
        HttpStatus status = mapErrorCodeToStatus(ex.getErrorCode());
        ApiError apiError = new ApiError(status.value(), ex.getErrorCode(), ex.getService(), ex.getPath());
        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleValidationException(ConstraintViolationException ex, HttpServletRequest request) {
        CommonException commonException = ExceptionFactory.genericError(
                ErrorCode.INVALID_INPUT,
                "unknown  service",
                request.getRequestURI()
        );
        return handleCommonException(commonException);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        CommonException commonException = ExceptionFactory.genericError(
                ErrorCode.USER_INVALID_ID,
                "unknown  service",
                request.getRequestURI()
        );
        return handleCommonException(commonException);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiError> handleNoResourceFound(NoResourceFoundException ex, HttpServletRequest request) {
        CommonException commonException = ExceptionFactory.genericError(
                ErrorCode.USER_NULL,
                SERVICE_NAME,
                request.getRequestURI()
        );
        return handleCommonException(commonException);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUnexpectedException(Exception ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ErrorCode.SYSTEM_ERROR,
                "unknown  service",
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private HttpStatus mapErrorCodeToStatus(ErrorCode errorCode) {
        switch (errorCode) {
            case USER_NOT_FOUND:
                return HttpStatus.NOT_FOUND;
            case USER_INVALID_ID:
            case INVALID_INPUT:
                return HttpStatus.BAD_REQUEST;
            case UNAUTHORIZED_ACCESS:
                return HttpStatus.UNAUTHORIZED;
            case RESOURCE_CONFLICT:
                return HttpStatus.CONFLICT;
            case ECHEQ_NOT_FOUND:
                return HttpStatus.NOT_FOUND;
            case SYSTEM_ERROR:
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
