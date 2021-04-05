package com.auth.keycloak.model;

/**
 * JWT response token
 */
public class LogoutResponse {
    private String code;
    private String message;

    /**
     * Logout response constructor
     * @param code
     * @param message
     */
    public LogoutResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public LogoutResponse() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

