package com.theinsideshine.services;

import com.theinsideshine.exceptions.ErrorCode;
import com.theinsideshine.exceptions.ExceptionFactory;
import com.theinsideshine.models.UserInfo;
import com.theinsideshine.models.UserInfoBasicDTO;
import com.theinsideshine.models.UserInfoFullDTO;
import com.theinsideshine.repositories.UserInfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserInfoPopulateService {

    private static final String SERVICE_NAME = "SERVICIO2-UserInfoPopulateService";
    private final UserInfoRepository repository;

    public UserInfoPopulateService(UserInfoRepository repository) {
        this.repository = repository;
    }

    public UserInfoBasicDTO getBasicUserInfo(Long userId, HttpServletRequest request) {
        String path = request.getRequestURI();
        log.info("[{}] Searching basic UserInfo for userId={}", SERVICE_NAME, userId);

        UserInfo userInfo = repository.findByUserId(userId);
        if (userInfo == null) {
            log.warn("[{}] Basic UserInfo not found for userId={}", SERVICE_NAME, userId);
            throw ExceptionFactory.resourceNotFound(ErrorCode.USER_NOT_FOUND, SERVICE_NAME, path);
        }

        log.info("[{}] Basic UserInfo found: userId={}, documentoTipo={}, direccion={}",
                SERVICE_NAME, userId, userInfo.getDocumentoTipo(), userInfo.getDireccion());

        return new UserInfoBasicDTO(
                userInfo.getDireccion(),
                userInfo.getTelefono(),
                userInfo.getDocumento(),
                userInfo.getDocumentoTipo()
        );
    }

    public UserInfoFullDTO getFullUserInfo(Long userId, HttpServletRequest request) {
        String path = request.getRequestURI();
        log.info("[{}] Searching full UserInfo for userId={}", SERVICE_NAME, userId);

        UserInfo userInfo = repository.findByUserId(userId);
        if (userInfo == null) {
            log.warn("[{}] Full UserInfo not found for userId={}", SERVICE_NAME, userId);
            throw ExceptionFactory.resourceNotFound(ErrorCode.USER_NOT_FOUND, SERVICE_NAME, path);
        }

        log.info("[{}] Full UserInfo found: userId={}, operador={}, adherente={}",
                SERVICE_NAME, userId, userInfo.getOperador(), userInfo.getAdherente());

        return new UserInfoFullDTO(
                userInfo.getDireccion(),
                userInfo.getTelefono(),
                userInfo.getDocumento(),
                userInfo.getDocumentoTipo(),
                userInfo.getOperador(),
                userInfo.getAdherente()
        );
    }
}
