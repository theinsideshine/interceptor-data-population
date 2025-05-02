package com.theinsideshine.controllers;

import com.theinsideshine.anotations.PopulateBasicUserInfo;
import com.theinsideshine.models.UserBasicDTO;
import com.theinsideshine.service.UserBasicService;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users/populate")
@Validated
public class UserBasicController {
    private static final String SERVICE_NAME = "SERVICIO1-UserBasicController";

    @Autowired
    private UserBasicService userBasicService;

    @GetMapping("/basic/{userId}")
    @PopulateBasicUserInfo
    public UserBasicDTO getBasicUser(@PathVariable("userId") @Min(1) Long userId) {
        log.info("[{}}] Received request for userId={}", SERVICE_NAME, userId);
        return userBasicService.getUserBasic(userId);
    }

}