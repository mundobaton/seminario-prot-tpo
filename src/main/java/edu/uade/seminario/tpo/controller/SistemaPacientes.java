package edu.uade.seminario.tpo.controller;

import edu.uade.seminario.tpo.exception.BusinessException;
import edu.uade.seminario.tpo.model.Dosis;
import edu.uade.seminario.tpo.model.Paciente;
import edu.uade.seminario.tpo.service.DosisService;
import edu.uade.seminario.tpo.service.IndicacionService;
import edu.uade.seminario.tpo.service.PacienteService;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class SistemaPacientes {

    @Inject
    private PacienteService pacienteService;
    @Inject
    private DosisService dosisService;

    public List<Paciente> getPacientes(Request request, Response response) {
        return pacienteService.getPacientes();
    }

    public Object getDosisPaciente(Request request, Response response) {
        String dniPaciente = request.params("dniPaciente");
        try {
            return pacienteService.buscarDosisPorPaciente(dniPaciente);
        } catch (BusinessException be) {
            response.status(be.getStatus());
            return be.getMessage();
        }
    }

    public List<Dosis> getDosis(Request request, Response response) {
        return dosisService.obtenerDosis();
    }

    public Object aplicarDosisPaciente(Request request, Response response) {
        Long dosisId = Long.parseLong((request.params("dosisId")));
        String email = request.queryParams("email");

        if (email == null || email.isEmpty()) {
            response.status(HttpStatus.BAD_REQUEST_400);
            return "El parámetro email es requerido";
        }
        try {
            dosisService.aplicarDosis(dosisId, email);
            return "";
        } catch (BusinessException be) {
            response.status(be.getStatus());
            return be.getMessage();
        }
    }
}
