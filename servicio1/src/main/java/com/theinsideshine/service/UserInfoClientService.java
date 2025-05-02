package com.theinsideshine.service;

import com.theinsideshine.client.UserInfoClient;
import com.theinsideshine.exceptions.ErrorCode;
import com.theinsideshine.exceptions.ExceptionFactory;
import com.theinsideshine.models.UserInfo;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserInfoClientService {

    private static final String SERVICE_NAME = "SERVICIO1-UserInfoClientService";

    @Autowired
    private UserInfoClient userInfoClient;

    public UserInfo getBasicInfo(Long userId, String requestPath) {
        log.info("[{}] Fetching user info for userId={}", SERVICE_NAME, userId);
        try {
            log.info("[{}] Successfully fetched user info for userId={}", SERVICE_NAME, userId);
            return userInfoClient.getBasicUserInfo(userId);
        } catch (FeignException.NotFound ex) {
            // Si el Servicio 2 responde 404, lanzar USER_NOT_FOUND
            log.warn("[{}] User not found for userId={}", SERVICE_NAME, userId);
            throw ExceptionFactory.resourceNotFound(ErrorCode.USER_NOT_FOUND, SERVICE_NAME, requestPath);
        } catch (FeignException.BadRequest ex) {
            // Si el Servicio 2 responde 400, lanzar USER_INVALID_ID
            log.warn("[{}] UserID invalid={}", SERVICE_NAME, userId);
            throw ExceptionFactory.badRequest(ErrorCode.USER_INVALID_ID, SERVICE_NAME, requestPath);
        } catch (FeignException ex) {
            // Otros errores Feign => Error general del sistema
            log.error("[{}] Feign error for userId={}: {}", SERVICE_NAME, userId, ex.getMessage(), ex);
            throw ExceptionFactory.genericError(ErrorCode.SYSTEM_ERROR, SERVICE_NAME, requestPath);
        }
    }

    public UserInfo getFullInfo(Long userId, String requestPath) {
        log.info("[{}] Fetching user info for userId={}", SERVICE_NAME, userId);
        try {
            log.info("[{}] Successfully fetched user info for userId={}", SERVICE_NAME, userId);
            return userInfoClient.getFullUserInfo(userId);
        } catch (FeignException.NotFound ex) {
            // Si el Servicio 2 responde 404, lanzar USER_NOT_FOUND
            log.warn("[{}] User not found for userId={}", SERVICE_NAME, userId);
            throw ExceptionFactory.resourceNotFound(ErrorCode.USER_NOT_FOUND, SERVICE_NAME, requestPath);
        } catch (FeignException.BadRequest ex) {
            // Si el Servicio 2 responde 400, lanzar USER_INVALID_ID
            log.warn("[{}] UserID invalid={}", SERVICE_NAME, userId);
            throw ExceptionFactory.badRequest(ErrorCode.USER_INVALID_ID, SERVICE_NAME, requestPath);
        } catch (FeignException ex) {
            // Otros errores Feign => Error general del sistema
            log.error("[{}] Feign error for userId={}: {}", SERVICE_NAME, userId, ex.getMessage(), ex);
            throw ExceptionFactory.genericError(ErrorCode.SYSTEM_ERROR, SERVICE_NAME, requestPath);
        }
    }
}
