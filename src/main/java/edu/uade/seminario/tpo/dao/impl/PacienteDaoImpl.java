package edu.uade.seminario.tpo.dao.impl;

import edu.uade.seminario.tpo.dao.AbstractDao;
import edu.uade.seminario.tpo.dao.PacienteDao;
import edu.uade.seminario.tpo.entity.PacienteEntity;
import edu.uade.seminario.tpo.model.Paciente;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class PacienteDaoImpl extends AbstractDao<PacienteEntity> implements PacienteDao {

    @Override
    public List<Paciente> findAll() {
        String query = "select p from PacienteEntity p where p.internado = :internado";
        try (Session session = getSession()) {
            Query<PacienteEntity> q = session.createQuery(query);
            q.setParameter("internado", true);
            List<PacienteEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                List<Paciente> pacientes = new ArrayList<>();
                for (PacienteEntity pe : result) {
                    pacientes.add(mapper.map(pe, Paciente.class));
                }
                return pacientes;
            }
        }
        return null;
    }

    @Override
    public Paciente findByDni(String dni) {
        String query = "select p from PacienteEntity p where p.dni = :dni";
        try (Session session = getSession()) {
            Query<PacienteEntity> q = session.createQuery(query);
            q.setParameter("dni", dni);
            List<PacienteEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                return mapper.map(result.get(0), Paciente.class);
            }
        }
        return null;
    }
}
