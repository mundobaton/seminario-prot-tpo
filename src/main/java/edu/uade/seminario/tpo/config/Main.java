package edu.uade.seminario.tpo.config;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Provides;
import edu.uade.seminario.tpo.controller.SistemaIndicaciones;
import edu.uade.seminario.tpo.controller.SistemaPacientes;
import edu.uade.seminario.tpo.controller.filter.ContentTypeFilter;
import edu.uade.seminario.tpo.dao.DosisDao;
import edu.uade.seminario.tpo.dao.IndicacionDao;
import edu.uade.seminario.tpo.dao.MedicamentoDao;
import edu.uade.seminario.tpo.dao.PacienteDao;
import edu.uade.seminario.tpo.dao.UsuarioDao;
import edu.uade.seminario.tpo.dao.impl.DosisDaoImpl;
import edu.uade.seminario.tpo.dao.impl.IndicacionDaoImpl;
import edu.uade.seminario.tpo.dao.impl.MedicamentoDaoImpl;
import edu.uade.seminario.tpo.dao.impl.PacienteDaoImpl;
import edu.uade.seminario.tpo.dao.impl.UsuarioDaoImpl;
import edu.uade.seminario.tpo.entity.DosisEntity;
import edu.uade.seminario.tpo.entity.IndicacionEntity;
import edu.uade.seminario.tpo.entity.ItemIndicacionEntity;
import edu.uade.seminario.tpo.entity.MedicamentoEntity;
import edu.uade.seminario.tpo.entity.PacienteEntity;
import edu.uade.seminario.tpo.entity.UsuarioEntity;
import edu.uade.seminario.tpo.model.ItemIndicacion;
import edu.uade.seminario.tpo.service.DosisService;
import edu.uade.seminario.tpo.service.IndicacionService;
import edu.uade.seminario.tpo.service.PacienteService;
import edu.uade.seminario.tpo.service.UsuarioService;
import edu.uade.seminario.tpo.service.impl.DosisServiceImpl;
import edu.uade.seminario.tpo.service.impl.IndicacionServiceImpl;
import edu.uade.seminario.tpo.service.impl.PacienteServiceImpl;
import edu.uade.seminario.tpo.service.impl.UsuarioServiceImpl;
import edu.uade.seminario.tpo.util.JsonTransformer;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.modelmapper.ModelMapper;
import spark.ResponseTransformer;

import javax.inject.Singleton;

import static spark.Spark.*;

public class Main extends Application {

    private SistemaIndicaciones sistemaIndicaciones;
    private SistemaPacientes sistemaPacientes;
    private ResponseTransformer responseTransformer;
    private ContentTypeFilter contentTypeFilter;

    public Main() {
        super();
        this.sistemaIndicaciones = Application.getInstance(SistemaIndicaciones.class);
        this.sistemaPacientes = Application.getInstance(SistemaPacientes.class);
        this.responseTransformer = Application.getInstance(JsonTransformer.class);
        this.contentTypeFilter = Application.getInstance(ContentTypeFilter.class);
    }

    public static void main(String[] args) {
        new Main().init();
    }

    @Override
    public void addRoutes() {

        after("/*", contentTypeFilter);
        path("/usuarios", () -> {
            post("/login", sistemaIndicaciones::loginUsuario, responseTransformer);
        });

        path("/pacientes", () -> {
            get("/:dniPaciente/dosis", sistemaPacientes::getDosisPaciente, responseTransformer);
            get("", sistemaPacientes::getPacientes, responseTransformer);
        });

        path("/indicaciones", () -> {
            put("", sistemaIndicaciones::generarIndicacion, responseTransformer);
            post("/:indicacionId/items", sistemaIndicaciones::agregarItemsIndicacion, responseTransformer);
            put("/:indicacionId", sistemaIndicaciones::finalizarCargaItems, responseTransformer);
            get("/search", sistemaIndicaciones::buscarIndicacionesPorEstado, responseTransformer);
            post("/:indicacionId", sistemaIndicaciones::modificarIndicacionRechazada, responseTransformer);
            get("/:indicacionId", sistemaIndicaciones::getIndicacion, responseTransformer);
            post("/:indicacionId/validate", sistemaIndicaciones::validarIndicacion, responseTransformer);
            post("/:indicacionId/send", sistemaIndicaciones::enviarIndicacion, responseTransformer);
            post("/:indicacionId/accept", sistemaIndicaciones::aceptarIndicacion, responseTransformer);
            post("/:indicacionId/reject", sistemaIndicaciones::rechazarIndicacion, responseTransformer);
        });

        path("/dosis", () -> {
            get("", sistemaPacientes::getDosis, responseTransformer);
            post("/:dosisId", sistemaPacientes::aplicarDosisPaciente, responseTransformer);
        });

        path("/medicamentos", () -> {
            get("", sistemaIndicaciones::getMedicamentos, responseTransformer);
        });
    }

    @Override
    protected void configure() {
        bind(UsuarioService.class).to(UsuarioServiceImpl.class);
        bind(UsuarioDao.class).to(UsuarioDaoImpl.class);
        bind(PacienteService.class).to(PacienteServiceImpl.class);
        bind(PacienteDao.class).to(PacienteDaoImpl.class);
        bind(IndicacionService.class).to(IndicacionServiceImpl.class);
        bind(IndicacionDao.class).to(IndicacionDaoImpl.class);
        bind(MedicamentoDao.class).to(MedicamentoDaoImpl.class);
        bind(DosisService.class).to(DosisServiceImpl.class);
        bind(DosisDao.class).to(DosisDaoImpl.class);
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

    @Provides
    public Gson provideGson() {
        return new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return (f.getName().equals("indicacion") && f.getDeclaringClass().equals(ItemIndicacion.class) ||
                        (f.getName().equals("dosis") && f.getDeclaringClass().equals(ItemIndicacion.class))
                );
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();
    }

    @Provides
    public ModelMapper provideMapper() {
        return new ModelMapper();
    }
}
