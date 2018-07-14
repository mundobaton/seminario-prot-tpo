package edu.uade.seminario.tpo.dao.impl;

import edu.uade.seminario.tpo.dao.AbstractDao;
import edu.uade.seminario.tpo.dao.UsuarioDao;
import edu.uade.seminario.tpo.entity.UsuarioEntity;
import edu.uade.seminario.tpo.model.Rol;
import edu.uade.seminario.tpo.model.Usuario;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class UsuarioDaoImpl extends AbstractDao<UsuarioEntity> implements UsuarioDao {

    @Override
    public Usuario findByEmail(String email) {
        String query = "select u from UsuarioEntity u where u.email = :email";
        try(Session session = getSession()) {
            Query<UsuarioEntity> q = session.createQuery(query);
            q.setParameter("email", email);
            List<UsuarioEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                return mapper.map(result.get(0), Usuario.class);
            }
        }
        return null;
    }

    @Override
    public Usuario findByEmailAndRole(String email, Rol rol) {
        String query = "select u from UsuarioEntity u where u.email = :email and u.rol = :rol";
        try(Session session = getSession()) {
            Query<UsuarioEntity> q = session.createQuery(query);
            q.setParameter("email", email);
            q.setParameter("rol", rol);
            List<UsuarioEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                return mapper.map(result.get(0), Usuario.class);
            }
        }
        return null;
    }
}
