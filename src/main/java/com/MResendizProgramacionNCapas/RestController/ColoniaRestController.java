
package com.MResendizProgramacionNCapas.RestController;

import com.MResendizProgramacionNCapas.DAO.ColoniaDAOImplementationJPA;
import com.MResendizProgramacionNCapas.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/colonia")
@CrossOrigin(origins = "http://localhost:8081" )
public class ColoniaRestController {

    @Autowired
    private ColoniaDAOImplementationJPA coloniaDAOImplementationJPA;
    
    @GetMapping("/{idMunicipio}")
   public ResponseEntity GetByMunicipio(@PathVariable int idMunicipio){
       Result result = new Result();
       
       try {
           
           result = coloniaDAOImplementationJPA.GetByIdMunicipio(idMunicipio);
           
       } catch (Exception ex) {
           result.correct = false;
           result.errorMessage = "No se encontro ninguna Colonia";
           result.status = 500;
       }
       
       return ResponseEntity.status(result.status).body(result.object);
   }
   
   
   @GetMapping("/codigoP/{codigoPostal}")
   public ResponseEntity GetByCodigoPostal(@PathVariable String codigoPostal){
       Result result = new Result();
       
       try {
           
           result = coloniaDAOImplementationJPA.GetByCodigoPostal(codigoPostal);
           
       } catch (Exception ex) {
           result.correct = false;
           result.errorMessage = "No se encontro ninguna Colonia";
           result.status = 500;
       }
       
       return ResponseEntity.status(result.status).body(result);
   }
    
}
