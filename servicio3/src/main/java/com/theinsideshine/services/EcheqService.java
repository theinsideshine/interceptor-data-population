package com.theinsideshine.services;

import com.theinsideshine.exceptions.ErrorCode;
import com.theinsideshine.exceptions.ExceptionFactory;
import com.theinsideshine.models.Echeq;
import com.theinsideshine.models.EcheqDTO;
import com.theinsideshine.repositories.EcheqRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EcheqService {

    private static final String SERVICE_NAME = "SERVICIO3-EcheqService";

    private final EcheqRepository echeqRepository;

    public EcheqService(EcheqRepository repository) {
        this.echeqRepository = repository;
    }

    public List<Echeq> findAll() {
        return echeqRepository.findAll();
    }

    public Optional<Echeq> findById(Long id) {
        return echeqRepository.findById(id);
    }

    public Echeq save(Echeq echeq) {
        return echeqRepository.save(echeq);
    }

    public void delete(Long id) {
        echeqRepository.deleteById(id);
    }


    public EcheqDTO getUserByOperador(String operador, String requestPath) {
        log.info("[{}] Searching Echeq for operador={}", SERVICE_NAME, operador);
        Echeq echeq = echeqRepository.findByOperadorBeneficiario(operador);

        if (echeq == null) {
            log.warn("[{}] No Echeq found for operador={}", SERVICE_NAME, operador);
            throw ExceptionFactory.echeqNotFound(ErrorCode.ECHEQ_NOT_FOUND, SERVICE_NAME, requestPath);
        }

        log.info("[{}] Echeq found: operador={}, chequeId={}", SERVICE_NAME, operador, echeq.getChequeId());

        return new EcheqDTO(
                echeq.getEstado(),
                echeq.getChequeId(),
                echeq.getBeneficiario(),
                echeq.getDocumentoBeneficiario(),
                echeq.getDocumentoTipoBeneficiario(),
                echeq.getOperadorBeneficiario(),
                echeq.getAdherenteBeneficiario()
        );
    }


}
