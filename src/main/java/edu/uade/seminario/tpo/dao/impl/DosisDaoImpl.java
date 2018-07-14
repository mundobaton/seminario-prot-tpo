package edu.uade.seminario.tpo.dao.impl;

import edu.uade.seminario.tpo.dao.AbstractDao;
import edu.uade.seminario.tpo.dao.DosisDao;
import edu.uade.seminario.tpo.entity.DosisEntity;
import edu.uade.seminario.tpo.model.Dosis;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class DosisDaoImpl extends AbstractDao<DosisEntity> implements DosisDao {

    @Override
    public Dosis findById(Long dosisId) {
        String query = "select d from DosisEntity d where d.id = :dosisId";
        try (Session session = getSession()) {
            Query<DosisEntity> q = session.createQuery(query);
            q.setParameter("dosisId", dosisId);
            List<DosisEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                return mapper.map(result.get(0), Dosis.class);
            }
        }

        return null;
    }

    @Override
    public void save(Dosis dosis) {
        DosisEntity entity = mapper.map(dosis, DosisEntity.class);
        save(entity);
    }
}
