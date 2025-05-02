package com.theinsideshine.controllers;

import com.theinsideshine.models.UserInfoBasicDTO;
import com.theinsideshine.models.UserInfoFullDTO;
import com.theinsideshine.services.UserInfoPopulateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

;


@Slf4j
@RestController
@RequestMapping("/user-info")
@Validated
public class UserInfoPopulateController {

    private static final String SERVICE_NAME = "SERVICIO2-UserInfoPopulateController";

    private final UserInfoPopulateService userInfoPopulateService;

    public UserInfoPopulateController(UserInfoPopulateService userInfoPopulateService) {
        this.userInfoPopulateService = userInfoPopulateService;
    }


    // Endpoint para obtener información básica del usuario (solo email)
    @GetMapping("/populateBasic/{userId}")
    public ResponseEntity<UserInfoBasicDTO> getBasicUserInfo(
            @PathVariable("userId") @NotNull @Min(1) Long userId,
            HttpServletRequest request) {
        log.info("[{}}] Received request for userId={}", SERVICE_NAME, userId);
        UserInfoBasicDTO basicInfo = userInfoPopulateService.getBasicUserInfo(userId, request);
        return ResponseEntity.ok(basicInfo);
    }

    // Endpoint para obtener información completa del usuario
    @GetMapping("/populateFull/{userId}")
    public ResponseEntity<UserInfoFullDTO> getFullUserInfo(
            @PathVariable("userId") @NotNull @Min(1) Long userId,
            HttpServletRequest request) {
        UserInfoFullDTO fullInfo = userInfoPopulateService.getFullUserInfo(userId, request);
        log.info("[{}}] Received request for userId={}", SERVICE_NAME, userId);
        return ResponseEntity.ok(fullInfo);
    }

}
