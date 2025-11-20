

package com.MResendizProgramacionNCapas.DAO;

import com.MResendizProgramacionNCapas.JPA.Result;


public interface IColoniaJPA {
    Result GetByIdMunicipio(int IdMunicipio);
    Result GetByCodigoPostal(String CodigoPostal);
}
