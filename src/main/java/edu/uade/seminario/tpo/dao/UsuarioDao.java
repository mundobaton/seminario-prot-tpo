package edu.uade.seminario.tpo.dao;

import edu.uade.seminario.tpo.model.Rol;
import edu.uade.seminario.tpo.model.Usuario;

public interface UsuarioDao {

    Usuario findByEmail(String email);

    Usuario findByEmailAndRole(String email, Rol rol);

}
