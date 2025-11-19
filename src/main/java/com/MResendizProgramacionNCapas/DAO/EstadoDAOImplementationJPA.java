
package com.MResendizProgramacionNCapas.DAO;

import com.MResendizProgramacionNCapas.JPA.EstadoJPA;
import com.MResendizProgramacionNCapas.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoDAOImplementationJPA implements IEstadoJPA{

    @Autowired
    private EntityManager entityManager;
    
    @Override
    public Result EstadoGetByIdPais(int IdPais) {
        Result result = new Result();
         
        try {
            
            TypedQuery<EstadoJPA> queryEstado = entityManager.createQuery("SELECT e FROM EstadoJPA e WHERE e.PaisJPA.IdPais = :IdPais", EstadoJPA.class);
            queryEstado.setParameter("IdPais", IdPais);
            List<EstadoJPA> estados = queryEstado.getResultList();
            
            result.object = estados;
            result.status = 200;
            result.correct = true;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;   
        }
        
        return result;
    }

}
