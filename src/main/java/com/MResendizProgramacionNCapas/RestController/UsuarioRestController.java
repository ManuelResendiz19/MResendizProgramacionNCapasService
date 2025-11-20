
package com.MResendizProgramacionNCapas.RestController;


import com.MResendizProgramacionNCapas.DAO.UsuarioDAOImplementationJPA;
import com.MResendizProgramacionNCapas.JPA.Result;
import com.MResendizProgramacionNCapas.JPA.UsuarioJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/usuario")
public class UsuarioRestController {

    @Autowired
    private UsuarioDAOImplementationJPA usuarioDAOImplementationJPA;

    
   @GetMapping
   public ResponseEntity GetAll(){
       Result result = new Result();
       
       try {
           
           result = usuarioDAOImplementationJPA.GetAll();
           result.correct = true;
           result.status = 200;
           
       } catch (Exception ex) {
           result.correct = false;
           result.errorMessage = ex.getLocalizedMessage();
           result.ex = ex;          
       }
       
       return ResponseEntity.status(result.status).body(result.object);
   }
   
    @GetMapping("/{idUsuario}")
    public ResponseEntity GetById(@PathVariable int idUsuario){
        Result result = new Result();
        
        try {
            result = usuarioDAOImplementationJPA.GetById(idUsuario);
            result.correct = true;
            result.status = 200;
            
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            
        }
        
        
        return ResponseEntity.status(result.status).body(result);
    }
   
   
   @PostMapping
   public ResponseEntity Add(UsuarioJPA usuarioJPA){
       Result result = new Result();
       
       try {
           result = usuarioDAOImplementationJPA.Add(usuarioJPA);
           result.correct = true;
           result.status = 201; 
           
       } catch (Exception ex) {
           result.correct = false;
           result.errorMessage = ex.getLocalizedMessage();
           result.ex = ex;
       }
       
       return ResponseEntity.status(result.status).body(result);
   }

}
