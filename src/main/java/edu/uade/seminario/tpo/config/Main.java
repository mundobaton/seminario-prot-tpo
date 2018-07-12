package edu.uade.seminario.tpo.config;

import com.google.gson.Gson;
import com.google.inject.Provides;
import edu.uade.seminario.tpo.controller.SistemaIndicaciones;
import edu.uade.seminario.tpo.entity.DosisEntity;
import edu.uade.seminario.tpo.entity.IndicacionEntity;
import edu.uade.seminario.tpo.entity.ItemIndicacionEntity;
import edu.uade.seminario.tpo.entity.MedicamentoEntity;
import edu.uade.seminario.tpo.entity.PacienteEntity;
import edu.uade.seminario.tpo.entity.UsuarioEntity;
import edu.uade.seminario.tpo.util.JsonTransformer;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ResponseTransformer;

import javax.inject.Singleton;

import static spark.Spark.*;

public class Main extends Application {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private SistemaIndicaciones sistemaIndicaciones;
    private ResponseTransformer responseTransformer;

    public Main() {
        super();
        this.sistemaIndicaciones = Application.getInstance(SistemaIndicaciones.class);
        this.responseTransformer = Application.getInstance(JsonTransformer.class);
    }

    public static void main(String[] args) {
        new Main().init();
    }

    @Override
    public void addRoutes() {
        get("/test", sistemaIndicaciones::ping, responseTransformer);
    }

    @Override
    protected void configure() {}

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

    @Provides
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    public ModelMapper provideMapper() {
        return new ModelMapper();
    }
}
