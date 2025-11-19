
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
@Table( name = "COLONIA")
public class ColoniaJPA {

    @Id
    @Column(name = "idcolonia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdColonia;
    
    @Column(name = "nombre")
    private String Nombre;
    
    @Column(name = "codigopostal")
    private String CodigoPostal;
    
    @ManyToOne
    @JoinColumn(name = "idmunicipio")
    @JsonIgnore
    public MunicipioJPA MunicipioJPA;

    
    
}
