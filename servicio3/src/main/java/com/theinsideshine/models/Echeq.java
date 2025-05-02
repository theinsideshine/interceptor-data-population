package com.theinsideshine.models;

import jakarta.persistence.*;

@Entity
@Table(name = "echeq")
public class Echeq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estado;
    private String chequeId;
    private String beneficiario;
    private String documentoBeneficiario;
    private String documentoTipoBeneficiario;
    private String operadorBeneficiario;
    private String adherenteBeneficiario;


    public Echeq(String estado, String chequeId, String beneficiario, String documentoBeneficiario,
                 String documentoTipoBeneficiario, String operadorBeneficiario,
                 String adherenteBeneficiario) {
        this.estado = estado;
        this.chequeId = chequeId;
        this.beneficiario = beneficiario;
        this.documentoBeneficiario = documentoBeneficiario;
        this.documentoTipoBeneficiario = documentoTipoBeneficiario;
        this.operadorBeneficiario = operadorBeneficiario;
        this.adherenteBeneficiario = adherenteBeneficiario;
    }

    public Echeq() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getChequeId() {
        return chequeId;
    }

    public void setChequeId(String chequeId) {
        this.chequeId = chequeId;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getDocumentoBeneficiario() {
        return documentoBeneficiario;
    }

    public void setDocumentoBeneficiario(String documentoBeneficiario) {
        this.documentoBeneficiario = documentoBeneficiario;
    }

    public String getDocumentoTipoBeneficiario() {
        return documentoTipoBeneficiario;
    }

    public void setDocumentoTipoBeneficiario(String documentoTipoBeneficiario) {
        this.documentoTipoBeneficiario = documentoTipoBeneficiario;
    }

    public String getOperadorBeneficiario() {
        return operadorBeneficiario;
    }

    public void setOperadorBeneficiario(String operadorBeneficiario) {
        this.operadorBeneficiario = operadorBeneficiario;
    }

    public String getAdherenteBeneficiario() {
        return adherenteBeneficiario;
    }

    public void setAdherenteBeneficiario(String adherenteBeneficiario) {
        this.adherenteBeneficiario = adherenteBeneficiario;
    }
}
