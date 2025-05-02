package com.theinsideshine.controllers;

import com.theinsideshine.anotations.PopulateEcheqInfo;
import com.theinsideshine.anotations.PopulateFullUserInfo;
import com.theinsideshine.models.EcheqDTO;
import com.theinsideshine.service.UserFullService;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/echeq")
@Validated
public class EcheqController {

    private static final String SERVICE_NAME = "SERVICIO1-EcheqController";
    @Autowired
    private UserFullService userFullService;


    @GetMapping("/echeq-operator/{userId}")
    @PopulateFullUserInfo
    @PopulateEcheqInfo
    public EcheqDTO getEcheqByOperador(@PathVariable("userId") @Min(1) Long userId, HttpServletRequest request) {
        EcheqDTO echeqInfo = (EcheqDTO) request.getAttribute("echeqInfo");
        log.info("[{}] UserId: {}", SERVICE_NAME, userId);
        log.info("[{}] Returning EcheqDTO: {}", SERVICE_NAME, echeqInfo);
        return echeqInfo;
    }
}
