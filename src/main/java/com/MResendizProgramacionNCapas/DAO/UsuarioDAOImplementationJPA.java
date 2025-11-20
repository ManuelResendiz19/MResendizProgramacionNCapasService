
package com.MResendizProgramacionNCapas.DAO;

import com.MResendizProgramacionNCapas.JPA.Result;
import com.MResendizProgramacionNCapas.JPA.RolJPA;
import com.MResendizProgramacionNCapas.JPA.UsuarioJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
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
            
            TypedQuery<UsuarioJPA> queryUsuario = entityManager.createQuery("FROM UsuarioJPA", UsuarioJPA.class);
            List<UsuarioJPA> usuarios = queryUsuario.getResultList();
         
            result.object = usuarios;
            result.correct = true;
            
            
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
            
            RolJPA rolJPA = entityManager.find(RolJPA.class, usuarioJPA.getRolJPA().getIdRols());
            usuarioJPA.setRolJPA(rolJPA);
 
            entityManager.persist(usuarioJPA);
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
                    result.correct = true;
                    result.object = usuarioJPA;
                }
        } catch (Exception ex) {
            result.correct =  false;
            result.errorMessage =  ex.getLocalizedMessage();
            result.ex =  ex;
            
        }
    
         return result;
     }
    
    
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public Result Update(UsuarioJPA usuarioJPA) {
//        Result result = new Result();
//        try {
//            
//            Optional<UsuarioJPA> usuarioOptional = usuarioRepo.findById(usuario.getIdUsuario());
//           
//           if(usuarioOptional.isPresent()){
//               UsuarioJPA usuarioJPA =  usuarioOptional.get();
//               UsuarioJPA usuarioUpdate = modelMapper.map(usuario, UsuarioJPA.class);
//               usuarioUpdate.setPassword(usuarioJPA.getPassword());
//               usuarioUpdate.setImagen(usuarioJPA.getImagen());
//               usuarioRepo.save(usuarioUpdate);
//           }
//         
//            result.correct =  true;
//            
//        } catch (Exception ex) {
//            result.correct =  false;
//            result.errorMessage =  ex.getLocalizedMessage();
//            result.ex =  ex;
//        }
//        
//        return result;
//    }



    @Override
    public Result Update(UsuarioJPA usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    


    
    
    
}
