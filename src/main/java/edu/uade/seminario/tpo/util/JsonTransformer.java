package edu.uade.seminario.tpo.util;

import com.google.gson.Gson;
import edu.uade.seminario.tpo.config.Config;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    private Gson gson = Config.getInjector(Config.APP).getInstance(Gson.class);

    @Override
    public String render(Object model) throws Exception {
        return gson.toJson(model);
    }
}
