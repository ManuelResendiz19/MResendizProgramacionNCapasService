
package com.MResendizProgramacionNCapas.DAO;

import com.MResendizProgramacionNCapas.JPA.ColoniaJPA;
import com.MResendizProgramacionNCapas.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ColoniaDAOImplementationJPA implements IColoniaJPA{

    @Autowired
    EntityManager entityManager;
    
    @Override
    public Result GetByIdMunicipio(int IdMunicipio) {
        
         Result result = new Result();
        
        try {
            
            TypedQuery<ColoniaJPA> queryColonia = entityManager.createQuery("SELECT colo FROM ColoniaJPA colo WHERE colo.MunicipioJPA.IdMunicipio = :IdMunicipio", ColoniaJPA.class);
            queryColonia.setParameter("IdMunicipio", IdMunicipio);
            List<ColoniaJPA> colonias = queryColonia.getResultList();
            
            result.object = colonias;
            result.correct = true;
            result.status = 200;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
        
    

    @Override
    public Result GetByCodigoPostal(String CodigoPostal) {
        Result result = new Result();
        
        try {
            
            TypedQuery<ColoniaJPA> queryColonia = entityManager.createQuery("SELECT colo FROM ColoniaJPA colo WHERE colo.CodigoPostal = :CodigoPostal", ColoniaJPA.class);
                queryColonia.setParameter("CodigoPostal", CodigoPostal);
            List<ColoniaJPA> colonias = queryColonia.getResultList();
            
            result.object = colonias;
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
