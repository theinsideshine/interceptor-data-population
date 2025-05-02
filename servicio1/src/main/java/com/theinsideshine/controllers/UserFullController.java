package com.theinsideshine.controllers;

import com.theinsideshine.anotations.PopulateFullUserInfo;
import com.theinsideshine.models.UserFullDTO;
import com.theinsideshine.service.UserFullService;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users/populate")
public class UserFullController {

    private static final String SERVICE_NAME = "SERVICIO1-UserFullController";
    @Autowired
    private UserFullService userFullService;

    @GetMapping("/full/{userId}")
    @PopulateFullUserInfo  // Activará el interceptor
    public UserFullDTO getFullUser(@PathVariable("userId") @Min(1) Long userId) {
        return userFullService.getUserFull(userId);
    }
}
