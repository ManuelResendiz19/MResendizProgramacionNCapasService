package com.MResendizProgramacionNCapas.RestController;

import com.MResendizProgramacionNCapas.DAO.EstadoDAOImplementationJPA;
import com.MResendizProgramacionNCapas.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/estado")
public class EstadoRestController {

    @Autowired
    private EstadoDAOImplementationJPA estadoDAOImplementationJPA;
    
    @GetMapping("/estado/{idPais}")
   public ResponseEntity EstadosGetByIdPais(@PathVariable int idPais){
       Result result = new Result();
       
       try {
           
           result = estadoDAOImplementationJPA.EstadoGetByIdPais(idPais);
           
       } catch (Exception ex) {
           result.correct = false;
           result.errorMessage = "No se encontro ningun Estado";
           result.status = 500;
       }
       
       return ResponseEntity.status(result.status).body(result.object);
   }
}