
package com.MResendizProgramacionNCapas.DAO;

import com.MResendizProgramacionNCapas.JPA.ColoniaJPA;
import com.MResendizProgramacionNCapas.JPA.DireccionJPA;
import com.MResendizProgramacionNCapas.JPA.Result;
import com.MResendizProgramacionNCapas.JPA.UsuarioJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DireccionDAOImplementationJPA implements IDireccionJPA{

    @Autowired
    private EntityManager entityManager;
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result DireccionAdd(DireccionJPA direccionJPA) {
        Result result = new Result();     
        try{
            
//            ColoniaJPA coloniaJPA = entityManager.find(ColoniaJPA.class, direccionJPA.getColoniaJPA().getIdColonia());
//            direccionJPA.setColoniaJPA(coloniaJPA);
//            
//            UsuarioJPA usuarioJPA = entityManager.find(UsuarioJPA.class, direccionJPA.getUsuarioJPA().getIdUsuario());
//            direccionJPA.setUsuarioJPA(usuarioJPA);
            
            entityManager.persist(direccionJPA);
            result.correct = true;
            result.status = 201;
       
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    
    @Override
    public Result DireccionGetById(int IdDireccion) {
        Result result = new Result();

        try {
            
            DireccionJPA direccionJPA = entityManager.find(DireccionJPA.class, IdDireccion);
             if(direccionJPA != null){
                    result.correct = true;
                    result.object = direccionJPA;
                }
           result.status = 200;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        
        return result;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result DireccionUpdate(DireccionJPA direccionJPA) {
        Result result = new Result();
        
        try {
            
            DireccionJPA direccionJPA1 = entityManager.find(DireccionJPA.class, direccionJPA.getIdDireccion());
            
            if(direccionJPA1 != null){

            entityManager.merge(direccionJPA);
            
            }  else {
                entityManager.persist(direccionJPA);
            }
            result.status = 202;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result DireccionDelete(int IdDireccion) {
        Result result = new Result();
        
        try {
            
            DireccionJPA direccionJPA = entityManager.find(DireccionJPA.class, IdDireccion);
            if(direccionJPA != null){
                    result.correct = true;
                    entityManager.remove(direccionJPA);
                }else{
                    result.correct = false;
                    result.errorMessage = "No se pudo encontrar a la direccion" + IdDireccion + "Para Eliminarlo";
                }
            
            result.status = 200;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }


}
