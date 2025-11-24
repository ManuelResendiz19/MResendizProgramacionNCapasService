
package com.MResendizProgramacionNCapas.RestController;

import com.MResendizProgramacionNCapas.DAO.DireccionDAOImplementationJPA;
import com.MResendizProgramacionNCapas.JPA.DireccionJPA;
import com.MResendizProgramacionNCapas.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
    
@RestController
@RequestMapping("api/direccion")
public class DireccionRestController {

    @Autowired
    private DireccionDAOImplementationJPA direccionDAOImplementationJPA;
    
    @GetMapping("/{IdDireccion}")
    public ResponseEntity DireccionesGetById(@PathVariable int IdDireccion){
        Result result = new Result();
        
        try {
            
            result = direccionDAOImplementationJPA.DireccionGetById(IdDireccion);
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        
        return ResponseEntity.status(result.status).body(result);
    }
    
    
    @GetMapping("/add")
    public ResponseEntity DireccionAdd(@RequestBody DireccionJPA direccionJPA){
        Result result = new Result();
        try {
            
            result = direccionDAOImplementationJPA.DireccionAdd(direccionJPA);
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return ResponseEntity.status(result.status).body(result);
    }
    
    
    
    
}