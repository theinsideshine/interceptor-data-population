package com.theinsideshine.service;


import com.theinsideshine.exceptions.ErrorCode;
import com.theinsideshine.exceptions.ExceptionFactory;
import com.theinsideshine.models.User;
import com.theinsideshine.models.UserFullDTO;
import com.theinsideshine.models.UserInfo;
import com.theinsideshine.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserFullService {

    private static final String SERVICE_NAME = "SERVICIO1-UserFullService";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpServletRequest request;

    public UserFullDTO getUserFull(Long userId) {
        log.info("[{}] Building Full user data for userId={}", SERVICE_NAME, userId);

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

        return new UserFullDTO(user, userInfo);
    }
}

