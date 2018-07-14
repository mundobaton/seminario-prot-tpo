package edu.uade.seminario.tpo.controller;

import edu.uade.seminario.tpo.model.Paciente;
import edu.uade.seminario.tpo.service.PacienteService;
import spark.Request;
import spark.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class SistemaPacientes {

    @Inject
    private PacienteService pacienteService;

    public List<Paciente> getPacientes(Request request, Response response) {
        return pacienteService.getPacientes();
    }
}
