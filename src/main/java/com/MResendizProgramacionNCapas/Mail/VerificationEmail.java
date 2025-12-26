
package com.MResendizProgramacionNCapas.Mail;

import java.io.Serializable;


public class VerificationEmail implements Serializable{
        
    private String email;
    private String token;
    private int IdUsuario;

    public VerificationEmail(){
    }
    
    public VerificationEmail(String email, String token, int IdUsuario){
        this.email = email;
        this.token = token;
        this.IdUsuario = IdUsuario;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }
    
    
}
