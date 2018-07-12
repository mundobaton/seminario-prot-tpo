package edu.uade.seminario.tpo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.io.Serializable;

public abstract class AbstractDao<T extends Serializable> {

    @Inject
    protected ModelMapper mapper;

    @Inject
    private SessionFactory sessionFactory;

    public T save(T t) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(t);
            session.getTransaction().commit();
            return t;
        }
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }

}
