package edu.uade.seminario.tpo.service;

import edu.uade.seminario.tpo.exception.BusinessException;
import edu.uade.seminario.tpo.model.Usuario;

public interface UsuarioService {

    Usuario login(String email, String password) throws BusinessException;


}
