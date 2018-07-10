package edu.uade.seminario.tpo.config;

import com.google.inject.Guice;
import edu.uade.seminario.tpo.config.injectors.UtilsModule;
import edu.uade.seminario.tpo.util.JsonTransformer;
import spark.ResponseTransformer;
import spark.servlet.SparkApplication;

import static spark.Spark.*;

public class Main implements SparkApplication {

    private ResponseTransformer responseTransformer;

    @Override
    public void init() {
        configure();
        initModules();
        loadDependencies();
        initRoutes();
    }

    @Override
    public void destroy() {
        stop();
    }

    private void configure() {
        port(Config.PORT);
    }

    private void initRoutes() {
        get("/ping", (req, res) -> "pong", responseTransformer);
    }

    public static void main(String[] args) {
        new Main().init();
    }

    private void loadDependencies() {
        responseTransformer = new JsonTransformer();
    }

    private void initModules() {
        Config.addInjector(Config.APP, Guice.createInjector(
                new UtilsModule()
        ));
    }

}
