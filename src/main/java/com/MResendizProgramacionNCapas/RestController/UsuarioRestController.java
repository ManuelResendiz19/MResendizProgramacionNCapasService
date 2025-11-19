
package com.MResendizProgramacionNCapas.RestController;

import com.MResendizProgramacionNCapas.DAO.EstadoDAOImplementationJPA;
import com.MResendizProgramacionNCapas.DAO.MunicipioDAOImplementationJPA;
import com.MResendizProgramacionNCapas.DAO.PaisDAOImplementationJPA;
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
    
    @Autowired
    private PaisDAOImplementationJPA paisDAOImplementationJPA;
    
    @Autowired
    private EstadoDAOImplementationJPA estadoDAOImplementationJPA;
    
    @Autowired
    private MunicipioDAOImplementationJPA municipioDAOImplementationJPA;
    
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
    
   
   
   
   
   
   
   @GetMapping("/pais")
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
   
   
   @GetMapping("/municipio/{idEstado}")
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
