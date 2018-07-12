package edu.uade.seminario.tpo.controller.filter;

import spark.Filter;
import spark.Request;
import spark.Response;

public class ContentTypeFilter implements Filter {

    @Override
    public void handle(Request request, Response response) throws Exception {
        response.header("Content-Type", "application/json");
    }
}
