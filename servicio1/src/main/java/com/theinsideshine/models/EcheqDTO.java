package com.theinsideshine.models;


public class EcheqDTO {

    private String estado;
    private String chequeId;
    private String beneficiario;
    private String documentoBeneficiario;
    private String documentoTipoBeneficiario;
    private String operadorBeneficiario;
    private String adherenteBeneficiario;

    // Constructor sin argumentos (necesario para Jackson)
    public EcheqDTO() {
    }

    public EcheqDTO(String estado, String chequeId, String beneficiario,
                    String documentoBeneficiario, String documentoTipoBeneficiario,
                    String operadorBeneficiario, String adherenteBeneficiario) {
        this.estado = estado;
        this.chequeId = chequeId;
        this.beneficiario = beneficiario;
        this.documentoBeneficiario = documentoBeneficiario;
        this.documentoTipoBeneficiario = documentoTipoBeneficiario;
        this.operadorBeneficiario = operadorBeneficiario;
        this.adherenteBeneficiario = adherenteBeneficiario;
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
