// ---- exceptions/ErrorCode.java ----
package com.theinsideshine.exceptions;

public enum ErrorCode {

    USER_NOT_FOUND(1001, "User not found", "No se encontró el usuario solicitado."),
    USER_INVALID_ID(1002, "Invalid user ID", "El identificador de usuario es inválido."),
    INVALID_INPUT(1003, "Invalid input", "Los datos ingresados no son válidos."),
    SYSTEM_ERROR(1004, "System error", "Ocurrió un error interno inesperado."),
    UNAUTHORIZED_ACCESS(1005, "Unauthorized access", "Acceso no autorizado."),
    RESOURCE_CONFLICT(1006, "Resource conflict", "Conflicto con el recurso solicitado."),
    ECHEQ_NOT_FOUND(2001, "Echeq not found", "No se encontró el Echeq solicitado."),
    ECHEQ_CLIENT_ERROR(2002, "Echeq client error", "Error al comunicarse con el servicio de Echeq."),
    ECHEQ_SERVICE_UNAVAILABLE(2003, "Echeq service unavailable", "El servicio de Echeq no está disponible.");


    private final int internalCode;
    private final String technicalMessage;
    private final String userMessage;

    ErrorCode(int internalCode, String technicalMessage, String userMessage) {
        this.internalCode = internalCode;
        this.technicalMessage = technicalMessage;
        this.userMessage = userMessage;
    }

    public int getInternalCode() {
        return internalCode;
    }

    public String getTechnicalMessage() {
        return technicalMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }
}