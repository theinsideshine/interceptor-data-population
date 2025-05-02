package com.theinsideshine.repositories;

import com.theinsideshine.models.Echeq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EcheqRepository extends JpaRepository<Echeq, Long> {
    Echeq findByOperadorBeneficiario(String operadorBeneficiario); // Método personalizado para encontrar por `operadorBeneficiario`

}

