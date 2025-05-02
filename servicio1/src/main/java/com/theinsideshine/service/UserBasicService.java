package com.theinsideshine.service;

import com.theinsideshine.exceptions.ErrorCode;
import com.theinsideshine.exceptions.ExceptionFactory;
import com.theinsideshine.models.User;
import com.theinsideshine.models.UserBasicDTO;
import com.theinsideshine.models.UserInfo;
import com.theinsideshine.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserBasicService {


    private static final String SERVICE_NAME = "SERVICIO1-UserBasicService";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpServletRequest request;

    public UserBasicDTO getUserBasic(Long userId) {
        log.info("[{}] Building basic user data for userId={}", SERVICE_NAME, userId);

        String path = request.getRequestURI();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.warn("[{}}] User nor found for for userId={}", SERVICE_NAME, userId);
                    return ExceptionFactory.resourceNotFound(ErrorCode.USER_NOT_FOUND, SERVICE_NAME, path);
                });

        log.info("[{}] User found: id={}, name={}, email={}", SERVICE_NAME, user.getId(), user.getName(), user.getEmail());

        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");

        if (userInfo == null) {
            log.warn("[{}] No UserInfo found in request for userId={}", SERVICE_NAME, userId);
            // Opcional lanzar excepción
        } else {
            log.info("[{}] UserInfo received: documentoTipo={}", SERVICE_NAME, userInfo.getDocumentoTipo());
        }

        return new UserBasicDTO(user, userInfo);
    }
}
