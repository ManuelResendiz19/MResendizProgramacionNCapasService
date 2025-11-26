
package com.MResendizProgramacionNCapas.RestController;

import com.MResendizProgramacionNCapas.DAO.PaisDAOImplementationJPA;
import com.MResendizProgramacionNCapas.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/pais")
@CrossOrigin(origins = "http://localhost:8081" )
public class PaisRestController {

    @Autowired
    private PaisDAOImplementationJPA paisDAOImplementationJPA;
    
    @GetMapping
   public ResponseEntity PaisGetAll(){
       Result result = new Result();
       
       try {
           
           result = paisDAOImplementationJPA.GetAll();
           
       } catch (Exception ex) {
           result.correct = false;
           result.errorMessage = "No se encontro ningun Pais";
           result.status = 500;
       }
       
       
       return ResponseEntity.status(result.status).body(result);
   }
}
