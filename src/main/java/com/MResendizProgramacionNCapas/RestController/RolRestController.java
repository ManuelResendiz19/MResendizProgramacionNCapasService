
package com.MResendizProgramacionNCapas.RestController;

import com.MResendizProgramacionNCapas.DAO.RolDAOImplementationJPA;
import com.MResendizProgramacionNCapas.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rol")
public class RolRestController {

    @Autowired
    private RolDAOImplementationJPA rolDAOImplementation;
    
    @GetMapping()
   public ResponseEntity RolGetAll(){
       Result result = new Result();
       
       try {
           
           result = rolDAOImplementation.GetAll();
           result.correct = true;
           result.status = 200;
           
       } catch (Exception ex) {
           result.correct = false;
           result.errorMessage = ex.getLocalizedMessage();
           result.ex = ex;          
       }
       
       return ResponseEntity.status(result.status).body(result);
   }
    
}
