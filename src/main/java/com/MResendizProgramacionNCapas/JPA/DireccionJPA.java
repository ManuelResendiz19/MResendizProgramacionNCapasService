
package com.MResendizProgramacionNCapas.JPA;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "DIRECCION")
public class DireccionJPA {
    
    @Id
    @Column(name = "iddireccion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdDireccion;
    
    @Column(name = "calle")
    private String Calle;
    
    @Column( name = "numeroexterior")
    private String NumeroExterior;
    
    @Column(name = "numerointerior")
    private String NumeroInterior;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario")
    @JsonIgnore
    public UsuarioJPA UsuarioJPA;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcolonia")
    @JsonIgnore
    public ColoniaJPA ColoniaJPA;

     public void setIdDireccion(int IdDireccion) {
        this.IdDireccion = IdDireccion;
    }
    
    public int getIdDireccion (){
        return IdDireccion;
    }

    public void setCalle(String Calle) {
        this.Calle = Calle;
    }
    
    
    public String getCalle() {
        return Calle;
    }

    
    public void setNumeroInterior(String NumeroInterior){
        this.NumeroInterior = NumeroInterior;
    }
    
    
    public String getNumeroInterior (){
        return NumeroInterior;
    }

    public String getNumeroExterior() {
        return NumeroExterior;
    }

    public void setNumeroExterior(String NumeroExterior) {
        this.NumeroExterior = NumeroExterior;
    }
    
}
