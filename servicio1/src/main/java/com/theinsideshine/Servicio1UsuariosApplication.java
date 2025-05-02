package com.theinsideshine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Servicio1UsuariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(Servicio1UsuariosApplication.class, args);
    }
}