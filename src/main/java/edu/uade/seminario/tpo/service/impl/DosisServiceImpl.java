package edu.uade.seminario.tpo.service.impl;

import edu.uade.seminario.tpo.dao.DosisDao;
import edu.uade.seminario.tpo.dao.UsuarioDao;
import edu.uade.seminario.tpo.exception.BusinessException;
import edu.uade.seminario.tpo.model.Dosis;
import edu.uade.seminario.tpo.model.Rol;
import edu.uade.seminario.tpo.model.Usuario;
import edu.uade.seminario.tpo.service.DosisService;
import org.eclipse.jetty.http.HttpStatus;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class DosisServiceImpl implements DosisService {

    @Inject
    private DosisDao dosisDao;
    @Inject
    private UsuarioDao usuarioDao;

    @Override
    public void aplicarDosis(Long dosisId, String email) throws BusinessException {
        Dosis dosis = dosisDao.findById(dosisId);
        if (dosis == null) {
            throw new BusinessException("No existe la dosis con id '" + dosisId.toString() + "'", HttpStatus.NOT_FOUND_404);
        }
        if (dosis.isAplicada()) {
            throw new BusinessException("La dosis ya ha sido aplicada");
        }
        Usuario enfermero = usuarioDao.findByEmailAndRole(email, Rol.ENFERMERO);
        if (enfermero == null) {
            throw new BusinessException("No se encontró el enfermero con email '" + email + "'", HttpStatus.NOT_FOUND_404);
        }
        dosis.aplicar(enfermero);
        dosisDao.save(dosis);
    }

    @Override
    public List<Dosis> obtenerDosis() {
        return dosisDao.findAll();
    }
}
