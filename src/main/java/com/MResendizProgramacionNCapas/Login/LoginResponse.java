
package com.MResendizProgramacionNCapas.Login;

import java.util.Date;


public class LoginResponse {
    private final String token;
    private final Date createdAt;
    private final Date expiresAt;
    private String rol;
    
    public LoginResponse(String token, Date createdAt, Date expiresAt, String rol ){
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.rol = rol;
    }
    
    public String getToken(){
        return token;
    }
    
    public Date getCreatedAt(){
        return createdAt;
    }
    
    public Date getExpiresAt(){
        return expiresAt;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
}
