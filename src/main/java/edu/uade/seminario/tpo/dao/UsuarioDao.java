package edu.uade.seminario.tpo.dao;

import edu.uade.seminario.tpo.model.Usuario;

public interface UsuarioDao {

    Usuario findByEmail(String email);

}
