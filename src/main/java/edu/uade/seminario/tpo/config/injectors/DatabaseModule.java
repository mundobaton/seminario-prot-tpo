package edu.uade.seminario.tpo.config.injectors;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import edu.uade.seminario.tpo.entity.DosisEntity;
import edu.uade.seminario.tpo.entity.IndicacionEntity;
import edu.uade.seminario.tpo.entity.ItemIndicacionEntity;
import edu.uade.seminario.tpo.entity.MedicamentoEntity;
import edu.uade.seminario.tpo.entity.PacienteEntity;
import edu.uade.seminario.tpo.entity.UsuarioEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.inject.Singleton;

public class DatabaseModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    public SessionFactory provideSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        configuration.addAnnotatedClass(DosisEntity.class);
        configuration.addAnnotatedClass(IndicacionEntity.class);
        configuration.addAnnotatedClass(ItemIndicacionEntity.class);
        configuration.addAnnotatedClass(MedicamentoEntity.class);
        configuration.addAnnotatedClass(PacienteEntity.class);
        configuration.addAnnotatedClass(UsuarioEntity.class);
        return configuration.buildSessionFactory(builder.build());
    }
}
