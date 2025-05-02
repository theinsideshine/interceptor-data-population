package com.theinsideshine.controllers;

import com.theinsideshine.models.UserInfo;
import com.theinsideshine.services.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user-info")
@Validated
public class UserInfoController {

    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    // Crear un nuevo UserInfo con id de usuario
    @PostMapping
    public ResponseEntity<UserInfo> createUserInfo(@RequestBody UserInfo userInfo) {
        UserInfo createdUserInfo = userInfoService.save(userInfo);
        return ResponseEntity.ok(createdUserInfo);
    }

    // Obtener todos los UserInfo
    @GetMapping
    public List<UserInfo> getAllUserInfo() {
        return userInfoService.findAll();
    }

    // Obtener un UserInfo por su ID
    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> getUserInfoById(@PathVariable("id") Long id) {
        Optional<UserInfo> userInfo = userInfoService.findById(id);
        return userInfo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Eliminar un UserInfo por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserInfo(@PathVariable("id") Long id) {
        Optional<UserInfo> existingUserInfo = userInfoService.findById(id);
        if (existingUserInfo.isPresent()) {
            userInfoService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
