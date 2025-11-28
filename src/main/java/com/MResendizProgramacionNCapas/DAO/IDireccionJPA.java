

package com.MResendizProgramacionNCapas.DAO;

import com.MResendizProgramacionNCapas.JPA.DireccionJPA;
import com.MResendizProgramacionNCapas.JPA.Result;
import com.MResendizProgramacionNCapas.JPA.UsuarioJPA;


public interface IDireccionJPA {

    Result DireccionGetById(int IdDireccion);
    Result DireccionAdd(DireccionJPA direccionJPA);
    Result DireccionUpdate(DireccionJPA direccionJPA);
    Result DireccionDelete(int IdDireccion);
}
