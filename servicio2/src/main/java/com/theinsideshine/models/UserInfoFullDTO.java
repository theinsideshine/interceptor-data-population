package com.theinsideshine.models;

// src/main/java/com/tu/paquete/dto/UserInfoFullDTO.java
public class UserInfoFullDTO {
    private String direccion;
    private String telefono;
    private String documento;
    private String documentoTipo;
    private String operador;
    private String adherente;

    public UserInfoFullDTO() {
    }

    public UserInfoFullDTO(String direccion, String telefono, String documento, String documentoTipo, String operador, String adherente) {
        this.direccion = direccion;
        this.telefono = telefono;
        this.documento = documento;
        this.documentoTipo = documentoTipo;
        this.operador = operador;
        this.adherente = adherente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelelefono(String telelefono) {
        this.telefono = telelefono;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDocumentoTipo() {
        return documentoTipo;
    }

    public void setDocumentoTipo(String documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getAdherente() {
        return adherente;
    }

    public void setAdherente(String adherente) {
        this.adherente = adherente;
    }
}
