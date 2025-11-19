
package com.MResendizProgramacionNCapas.JPA;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ESTADO")
public class EstadoJPA {

    @Id
    @Column(name = "idestado")
    private int IdEstado;
    
    @Column(name = "nombre")
    private String Nombre;
    
    @ManyToOne
    @JoinColumn(name = "idpais")
    @JsonIgnore
    public PaisJPA PaisJPA;

    public int getIdEstado() {
        return IdEstado;
    }

    public void setIdEstado(int IdEstado) {
        this.IdEstado = IdEstado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
}
