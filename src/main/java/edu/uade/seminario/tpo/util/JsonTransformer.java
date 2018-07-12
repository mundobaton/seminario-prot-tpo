package edu.uade.seminario.tpo.util;

import com.google.gson.Gson;
import edu.uade.seminario.tpo.config.Config;
import org.hibernate.SessionFactory;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    private Gson gson = Config.getInjector(Config.APP).getInstance(Gson.class);
    private SessionFactory sessionFactory = Config.getInjector(Config.APP).getInstance(SessionFactory.class);

    @Override
    public String render(Object model) throws Exception {
        System.out.print(sessionFactory == null);
        return gson.toJson(model);
    }
}
