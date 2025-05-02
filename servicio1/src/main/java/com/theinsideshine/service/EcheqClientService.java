package com.theinsideshine.service;

import com.theinsideshine.client.EcheqClient;
import com.theinsideshine.exceptions.EcheqNotFoundException;
import com.theinsideshine.exceptions.ErrorCode;
import com.theinsideshine.models.EcheqDTO;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EcheqClientService {

    private static final String SERVICE_NAME = "SERVICIO1-EcheqClientService";

    @Autowired
    private EcheqClient echeqClient;

    public EcheqDTO getEcheqByOperador(String operador, String requestPath) {
        log.info("[{}] Fetching Echeq info for operador={}", SERVICE_NAME, operador);
        try {
            EcheqDTO echeqDTO = echeqClient.getEcheqByOperador(operador);
            log.info("[{}] Successfully fetched Echeq info for operador={}", SERVICE_NAME, operador);
            return echeqDTO;
        } catch (FeignException.NotFound ex) {
            log.warn("[{}] Echeq not found for operador={}", SERVICE_NAME, operador);
            throw new EcheqNotFoundException(ErrorCode.ECHEQ_NOT_FOUND, SERVICE_NAME, requestPath);
        } catch (FeignException.ServiceUnavailable ex) {
            log.error("[{}] Servicio 3 unavailable for operador={}: {}", SERVICE_NAME, operador, ex.getMessage());
            throw new EcheqNotFoundException(ErrorCode.ECHEQ_SERVICE_UNAVAILABLE, SERVICE_NAME, requestPath);
        } catch (FeignException ex) {
            log.error("[{}] Feign error fetching Echeq info for operador={}: {}", SERVICE_NAME, operador, ex.getMessage(), ex);
            throw new EcheqNotFoundException(ErrorCode.ECHEQ_CLIENT_ERROR, SERVICE_NAME, requestPath);
        }
    }
}