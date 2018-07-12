package edu.uade.seminario.tpo.util;

import com.google.gson.Gson;
import edu.uade.seminario.tpo.config.Config;
import spark.ResponseTransformer;

import javax.inject.Inject;

public class JsonTransformer implements ResponseTransformer {

    @Inject
    private Gson gson;

    @Override
    public String render(Object model) throws Exception {
        return gson.toJson(model);
    }
}
