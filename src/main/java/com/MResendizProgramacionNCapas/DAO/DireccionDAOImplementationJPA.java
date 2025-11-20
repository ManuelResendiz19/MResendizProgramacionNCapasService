
package com.MResendizProgramacionNCapas.DAO;

import com.MResendizProgramacionNCapas.JPA.DireccionJPA;
import com.MResendizProgramacionNCapas.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DireccionDAOImplementationJPA implements IDireccionJPA{

    @Autowired
    private EntityManager entitiManager;
    
    
    @Override
    public Result DireccionGetById(int IdDireccion) {
        Result result = new Result();

        try {
            
            DireccionJPA direccionJPA = entitiManager.find(DireccionJPA.class, IdDireccion);
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

}
