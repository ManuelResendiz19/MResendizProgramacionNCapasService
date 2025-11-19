
package com.MResendizProgramacionNCapas.DAO;

import com.MResendizProgramacionNCapas.JPA.MunicipioJPA;
import com.MResendizProgramacionNCapas.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MunicipioDAOImplementationJPA implements IMunicipioJPA{

    @Autowired
    EntityManager entityManager;
    
    
    @Override
    public Result MunicipioGetByIdPais(int IdEstado) {
        Result result = new Result();
        
        try {
            
            TypedQuery<MunicipioJPA> queryMunicipios = entityManager.createQuery("SELECT m FROM MunicipioJPA m WHERE m.EstadoJPA.IdEstado = :IdEstado", MunicipioJPA.class);
            queryMunicipios.setParameter("IdEstado", IdEstado);
            List<MunicipioJPA> municipios = queryMunicipios.getResultList();
            
            result.object = municipios;
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
