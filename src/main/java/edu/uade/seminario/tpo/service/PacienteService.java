package edu.uade.seminario.tpo.service;

import edu.uade.seminario.tpo.model.Paciente;

import java.util.List;

public interface PacienteService {

    List<Paciente> getPacientes();

    Paciente buscarPaciente(String dni);

}
