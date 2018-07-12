package edu.uade.seminario.tpo.controller;

import org.hibernate.SessionFactory;
import spark.Request;
import spark.Response;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SistemaIndicaciones {

    public String ping(Request request, Response response) {
        return "pong";
    }

}
