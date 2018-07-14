package edu.uade.seminario.tpo.service.impl;

import edu.uade.seminario.tpo.dao.UsuarioDao;
import edu.uade.seminario.tpo.exception.BusinessException;
import edu.uade.seminario.tpo.model.Usuario;
import edu.uade.seminario.tpo.service.UsuarioService;
import org.eclipse.jetty.http.HttpStatus;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    private UsuarioDao usuarioDao;

    @Override
    public Usuario login(String email, String password) throws BusinessException {
        Usuario u = usuarioDao.findByEmail(email);
        if (u == null) {
            throw new BusinessException("El usuario con email '" + email + "' no existe o la contraseña es incorrecta", HttpStatus.FORBIDDEN_403);
        }

        if (!u.esActivo()) {
            throw new BusinessException("El usuario está inactivo", HttpStatus.FORBIDDEN_403);
        }

        if (!u.esPasswordValido(password)) {
            throw new BusinessException("El usuario con email '" + email + "' no existe o la contraseña es incorrecta", HttpStatus.FORBIDDEN_403);
        }
        return u;
    }
}
