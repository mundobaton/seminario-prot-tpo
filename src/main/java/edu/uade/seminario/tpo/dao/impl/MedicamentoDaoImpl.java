package edu.uade.seminario.tpo.dao.impl;

import edu.uade.seminario.tpo.dao.AbstractDao;
import edu.uade.seminario.tpo.dao.MedicamentoDao;
import edu.uade.seminario.tpo.entity.MedicamentoEntity;
import edu.uade.seminario.tpo.model.Medicamento;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class MedicamentoDaoImpl extends AbstractDao<MedicamentoEntity> implements MedicamentoDao {

    @Override
    public Medicamento findById(Long medicamentoId) {
        String query = "select m from MedicamentoEntity m where m.id = :medicamentoId";
        try (Session session = getSession()) {
            Query<MedicamentoEntity> q = session.createQuery(query);
            q.setParameter("medicamentoId", medicamentoId);
            List<MedicamentoEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                return mapper.map(result.get(0), Medicamento.class);
            }
        }
        return null;
    }

    @Override
    public List<Medicamento> findAll() {
        String query = "select m from MedicamentoEntity m";
        try (Session session = getSession()) {
            Query<MedicamentoEntity> q = session.createQuery(query);
            List<MedicamentoEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                List<Medicamento> medicamentos = new ArrayList<>();
                for (MedicamentoEntity entity : result) {
                    medicamentos.add(mapper.map(entity, Medicamento.class));
                }
                return medicamentos;
            }
        }
        return new ArrayList();
    }
}
