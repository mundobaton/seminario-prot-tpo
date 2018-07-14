package edu.uade.seminario.tpo.dao.impl;

import edu.uade.seminario.tpo.dao.AbstractDao;
import edu.uade.seminario.tpo.dao.IndicacionDao;
import edu.uade.seminario.tpo.entity.IndicacionEntity;
import edu.uade.seminario.tpo.model.EstadoIndicacion;
import edu.uade.seminario.tpo.model.Indicacion;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class IndicacionDaoImpl extends AbstractDao<IndicacionEntity> implements IndicacionDao {

    @Override
    public Indicacion save(Indicacion indicacion) {
        IndicacionEntity entity = mapper.map(indicacion, IndicacionEntity.class);
        entity = save(entity);
        return mapper.map(entity, Indicacion.class);
    }

    @Override
    public Indicacion findById(Long indicacionId) {
        String query = "select i from IndicacionEntity i where i.codigoIndicacion = :indicacionId";
        try (Session session = getSession()) {
            Query<IndicacionEntity> q = session.createQuery(query);
            q.setParameter("indicacionId", indicacionId);
            List<IndicacionEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                return mapper.map(result.get(0), Indicacion.class);
            }
        }
        return null;
    }

    @Override
    public List<Indicacion> findByEstado(EstadoIndicacion estadoIndicacion) {
        String query = "select i from IndicacionEntity i where i.estado = :estado";
        try (Session session = getSession()) {
            Query<IndicacionEntity> q = session.createQuery(query);
            q.setParameter("estado", estadoIndicacion);
            List<IndicacionEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                List<Indicacion> indicaciones = new ArrayList<>();
                for (IndicacionEntity ie : result) {
                    indicaciones.add(mapper.map(ie, Indicacion.class));
                }
                return indicaciones;
            }
        }
        return new ArrayList<>();
    }
}
