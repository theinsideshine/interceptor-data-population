package com.theinsideshine.client;


import com.theinsideshine.models.EcheqDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "servicio3", url = "http://localhost:8083") // Ajusta el puerto según tu config
public interface EcheqClient {

    @GetMapping("/echeq/beneficiario-operador/{operador}")
        // Ajusta la ruta del endpoint del Servicio 3
    EcheqDTO getEcheqByOperador(@PathVariable("operador") String operador);
}
