package com.theinsideshine.models;

import jakarta.persistence.*;

@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String direccion;
    private String telefono;
    private String documento;
    private String documentoTipo;
    private String operador;
    private String adherente;

    @Column(name = "user_id")  // Guardamos solo el id del usuario del Servicio 1
    private Long userId;

    // Constructores
    public UserInfo() {
    }

    public UserInfo(String direccion, String telelefono, String documento, String documentoTipo, String operador, String adherente, Long userId) {
        this.direccion = direccion;
        this.telefono = telelefono;
        this.documento = documento;
        this.documentoTipo = documentoTipo;
        this.operador = operador;
        this.adherente = adherente;
        this.userId = userId;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setTelefono(String telefono) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
