
package com.MResendizProgramacionNCapas.DAO;

import com.MResendizProgramacionNCapas.JPA.Result;
import com.MResendizProgramacionNCapas.JPA.RolJPA;
import com.MResendizProgramacionNCapas.JPA.UsuarioJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImplementationJPA implements IUsuarioJPA{

    @Autowired
    private EntityManager entityManager;

    
    @Override
    public Result GetAll() {
        Result result = new Result();
        try{
            
            TypedQuery<UsuarioJPA> queryUsuario = entityManager.createQuery("SELECT u FROM UsuarioJPA u LEFT JOIN FETCH u.DireccionesJPA", UsuarioJPA.class);
            List<UsuarioJPA> usuarios = queryUsuario.getResultList();
            
             for (UsuarioJPA usuario : usuarios) {
            Hibernate.initialize(usuario.getDireccionesJPA());
            }
             
            result.object = usuarios;
            result.correct = true;
            result.status = 200;
            
            
        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result Add(UsuarioJPA usuarioJPA) {

        Result result = new Result();     
        try{
            
//            RolJPA rolJPA = entityManager.find(RolJPA.class, usuarioJPA.getRolJPA().getIdRols());
//            usuarioJPA.setRolJPA(rolJPA);
//            usuarioJPA.setIsVerified(0);
            usuarioJPA.DireccionesJPA.get(0).UsuarioJPA = usuarioJPA;
            entityManager.persist(usuarioJPA);
            entityManager.flush();
            
            result.object = usuarioJPA;
            
//            DireccionJPA direccion = usuarioJPA.DireccionesJPA.get(0);
//            direccion.ColoniaJPA = new ColoniaJPA();
//            direccion.UsuarioJPA = new UsuarioJPA();
//            direccion.ColoniaJPA.setIdColonia(usuarioJPA.DireccionesJPA.get(0).ColoniaJPA.getIdColonia());
//            direccion.UsuarioJPA.setIdUsuario(usuarioJPA.getIdUsuario());
//            
//            entityManager.persist(direccion);
            
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
     public Result GetById(int IdUsuario){
         Result result = new Result();
         
         try {
                
                UsuarioJPA usuarioJPA = entityManager.find(UsuarioJPA.class, IdUsuario);
                if(usuarioJPA != null){
                    result.object = usuarioJPA;
                    result.correct = true;
                    result.status = 200;
                }
        } catch (Exception ex) {
            result.correct =  false;
            result.errorMessage =  ex.getLocalizedMessage();
            result.ex =  ex;
            
        }
    
         return result;
     }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result Update(UsuarioJPA usuario) {
        Result result = new Result();
        
        try {
            
            UsuarioJPA usuarioUpda = entityManager.find(UsuarioJPA.class, usuario.getIdUsuario());
            
            if(usuarioUpda != null){
            usuario.setPassword(usuarioUpda.getPassword());
            usuario.setImagen(usuarioUpda.getImagen());
            
            if(usuario.getRolJPA() != null){
                RolJPA roljpa =  entityManager.find(RolJPA.class, usuario.getRolJPA().getIdRols());
                usuario.setRolJPA(roljpa);
            }
            
            
//            if(usuario.getDireccionesJPA() != null){
//                DireccionJPA direccionJPA = entityManager.find(DireccionJPA.class, direccionJPA.getIdDireccion());
//                usuario.setDireccionesJPA(direccionJPA);
//            }
            entityManager.merge(usuario);
            
            }  else {
                entityManager.persist(usuario);
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
    public Result Delete(int IdUsuario) {
        Result result = new Result();
        
        try {
            
            UsuarioJPA usuarioJPA = entityManager.find(UsuarioJPA.class, IdUsuario);
            if(usuarioJPA != null){
                    result.correct = true;
                    entityManager.remove(usuarioJPA);                    
                }else{
                    result.correct = false;
                    result.errorMessage = "No se pudo encontrar al usuario" + IdUsuario + "Para Eliminarlo";
                }
            
            result.status = 200;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        
        return result;
    }
    
    
    @Override
    public Result GetAllDynamic(String busqueda) {
        Result result = new Result();
        try {

            String jpql = "SELECT u FROM UsuarioJPA u";
            if (busqueda != null && !busqueda.trim().isEmpty()) {
                jpql += " WHERE LOWER(u.Nombre) LIKE :patron OR LOWER(u.ApellidoPaterno) LIKE :patron OR LOWER(u.ApellidoMaterno) LIKE :patron";
            }
            TypedQuery<UsuarioJPA> queryUsuario = entityManager.createQuery(jpql, UsuarioJPA.class);
            if (busqueda != null && !busqueda.trim().isEmpty()) {
                queryUsuario.setParameter("patron", "%" + busqueda.toLowerCase() + "%");
            }

            List<UsuarioJPA> usuarios = queryUsuario.getResultList();

            result.object = usuarios;
            result.correct = true;
            result.status = 200;

        }catch(Exception ex){
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
