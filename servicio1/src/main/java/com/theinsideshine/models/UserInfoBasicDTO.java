package com.theinsideshine.models;

// src/main/java/com/tu/paquete/dto/UserInfoBasicDTO.java
public class UserInfoBasicDTO {
    private String direccion;
    private String telefono;
    private String documento;
    private String documentoTipo;

    public UserInfoBasicDTO(String direccion, String telefono, String documento, String documentoTipo) {
        this.direccion = direccion;
        this.telefono = telefono;
        this.documento = documento;
        this.documentoTipo = documentoTipo;
    }

    public UserInfoBasicDTO(UserInfo userInfo) {
        this.direccion = userInfo.getDireccion();
        this.telefono = userInfo.getTelefono();
        this.documento = userInfo.getDocumento();
        this.documentoTipo = userInfo.getDocumentoTipo();
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

    public void setTelelefono(String telefono) {
        this.telefono = telefono;
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
}
