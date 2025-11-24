
package com.MResendizProgramacionNCapas.RestController;


import com.MResendizProgramacionNCapas.DAO.UsuarioDAOImplementationJPA;
import com.MResendizProgramacionNCapas.JPA.Result;
import com.MResendizProgramacionNCapas.JPA.UsuarioJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
           
       } catch (Exception ex) {
           result.correct = false;
           result.errorMessage = ex.getLocalizedMessage();
           result.ex = ex;          
       }
       
       return ResponseEntity.status(result.status).body(result);
   }
   
   
    @GetMapping("/{idUsuario}")
    public ResponseEntity GetById(@PathVariable int idUsuario){
        Result result = new Result();
        
        try {
            result = usuarioDAOImplementationJPA.GetById(idUsuario);
            
            
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            
        }
        
        
        return ResponseEntity.status(result.status).body(result);
    }
   
   
   @PostMapping("/add")
   public ResponseEntity Add(@RequestBody UsuarioJPA usuarioJPA){
       Result result = new Result();
       
       try {
           result = usuarioDAOImplementationJPA.Add(usuarioJPA);
           
       } catch (Exception ex) {
           result.correct = false;
           result.errorMessage = ex.getLocalizedMessage();
           result.ex = ex;
       }
       
       return ResponseEntity.status(result.status).body(result);
   }
   
   
   
   @PutMapping("/update")
   public ResponseEntity Update(@RequestBody UsuarioJPA usuarioJPA){
       Result result = new Result();
       
       try {
           
           result = usuarioDAOImplementationJPA.Update(usuarioJPA);
           
       } catch (Exception ex) {
           result.correct =false;
           result.errorMessage = "Nose pudo Actualizar el Usuario";
           result.ex = ex;
           result.status = 500;
       }
       
       return ResponseEntity.status(result.status).body(result);
   }

   
   
   @DeleteMapping("/delete/{IdUsuario}")
   public ResponseEntity Delete(@PathVariable int IdUsuario){
       Result result = new Result();
       try {
           result = usuarioDAOImplementationJPA.Delete(IdUsuario);
           
       } catch (Exception ex) {
           result.correct = false;
           result.errorMessage = "El Usuario no se pudo eliminar";
           result.ex = ex;
           result.status = 500;
       }
  
       return ResponseEntity.status(result.status).body(result);
   }
   
   @GetMapping("/busqueda")
   public ResponseEntity GetAllDynamic(@RequestParam String busqueda){
       Result result = new Result();
       
       try {
            
           result = usuarioDAOImplementationJPA.GetAllDynamic(busqueda);
           
       } catch (Exception ex) {
           result.correct = false;
           result.errorMessage = ex.getLocalizedMessage();
           result.ex = ex;
       }
       
       return ResponseEntity.status(result.status).body(result);
   }
}
