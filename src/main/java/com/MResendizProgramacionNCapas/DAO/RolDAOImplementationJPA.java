
package com.MResendizProgramacionNCapas.DAO;

import com.MResendizProgramacionNCapas.JPA.Result;
import com.MResendizProgramacionNCapas.JPA.RolJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RolDAOImplementationJPA implements IRolJPA{

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetAll() {
        Result result = new Result();
        
        try {
            
            TypedQuery<RolJPA> queryRol = entityManager.createQuery("FROM RolJPA",RolJPA.class);
            List<RolJPA> roles = queryRol.getResultList();
         
            result.object = roles;
            result.correct = true;
            result.status = 200;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
    
    
}
