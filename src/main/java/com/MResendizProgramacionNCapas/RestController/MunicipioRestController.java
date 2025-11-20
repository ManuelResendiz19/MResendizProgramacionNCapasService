
package com.MResendizProgramacionNCapas.RestController;

import com.MResendizProgramacionNCapas.DAO.MunicipioDAOImplementationJPA;
import com.MResendizProgramacionNCapas.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/municipio")
public class MunicipioRestController {

    @Autowired
    private MunicipioDAOImplementationJPA municipioDAOImplementationJPA;
    
    @GetMapping("/{idEstado}")
   public ResponseEntity MunicipioGetByIdEstado(@PathVariable int idEstado){
       Result result = new Result();
       
       try {
           
           result = municipioDAOImplementationJPA.MunicipioGetByIdPais(idEstado);
           
       } catch (Exception ex) {
           result.correct = false;
           result.errorMessage = "No se encontro ningun Municipio";
           result.status = 500;
       }
       
       return ResponseEntity.status(result.status).body(result.object);
   }
}
