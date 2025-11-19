

package com.MResendizProgramacionNCapas.DAO;

import com.MResendizProgramacionNCapas.JPA.Result;
import com.MResendizProgramacionNCapas.JPA.UsuarioJPA;


public interface IUsuarioJPA {

        Result GetAll();
        Result Add(UsuarioJPA usuarioJPA);
        Result GetById(int IdUsuario);
        Result Update(UsuarioJPA usuario);
        
}
