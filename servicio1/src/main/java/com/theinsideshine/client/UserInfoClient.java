package com.theinsideshine.client;

import com.theinsideshine.models.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "servicio2", url = "http://localhost:8082") // Ajustá el puerto según tu config
public interface UserInfoClient {

    @GetMapping("/user-info/populateFull/{userId}")
    UserInfo getFullUserInfo(@PathVariable("userId") Long userId);

    @GetMapping("/user-info/populateBasic/{userId}")
    UserInfo getBasicUserInfo(@PathVariable("userId") Long userId);
}
