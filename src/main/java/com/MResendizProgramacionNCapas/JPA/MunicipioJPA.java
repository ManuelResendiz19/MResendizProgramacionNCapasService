
package com.MResendizProgramacionNCapas.JPA;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "MUNICIPIO")
public class MunicipioJPA {

    @Id
    @Column(name = "idmunicipio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdMunicipio;
    
    @Column(name = "nombre")
    private String Nombre;
    
    @ManyToOne
    @JoinColumn(name = "idestado")
    @JsonIgnore
    public EstadoJPA EstadoJPA;
    
    public int getIdMunicioio() {
        return IdMunicipio;
    }

    public void setIdMunicioio(int IdMunicioio) {
        this.IdMunicipio = IdMunicioio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
}
