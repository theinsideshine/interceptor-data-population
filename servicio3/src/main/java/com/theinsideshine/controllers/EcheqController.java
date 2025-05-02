package com.theinsideshine.controllers;


import com.theinsideshine.models.Echeq;
import com.theinsideshine.models.EcheqDTO;
import com.theinsideshine.services.EcheqService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/echeq")
@Validated
public class EcheqController {

    private static final String SERVICE_NAME = "SERVICIO3-EcheqController";

    private final EcheqService echeqService;

    public EcheqController(EcheqService echeqService) {
        this.echeqService = echeqService;
    }

    // Crear un nuevo Echeq
    @PostMapping
    public ResponseEntity<Echeq> createEcheq(@RequestBody Echeq echeq) {
        Echeq createdEcheq = echeqService.save(echeq);
        return ResponseEntity.ok(createdEcheq);
    }

    // Obtener todos los Echeq
    @GetMapping
    public List<Echeq> getAllEcheq() {
        return echeqService.findAll();
    }

    @GetMapping("beneficiario-operador/{operador}")
    public ResponseEntity<EcheqDTO> getEcheqByOperador(
            @PathVariable("operador") @NotBlank String operador,
            HttpServletRequest request) {
        String path = request.getRequestURI();
        log.info("[{}] Received request for operador={} from: {}", SERVICE_NAME, operador, path);
        EcheqDTO echeqDTO = echeqService.getUserByOperador(operador, path);
        log.info("[{}] Echeq found for operador={}: {}", SERVICE_NAME, operador, echeqDTO);
        return ResponseEntity.ok(echeqDTO);
    }


    // Eliminar un Echeq por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEcheq(@PathVariable("id") Long id) {
        Optional<Echeq> existingEcheq = echeqService.findById(id);
        if (existingEcheq.isPresent()) {
            echeqService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
