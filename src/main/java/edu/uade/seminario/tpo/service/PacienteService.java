package edu.uade.seminario.tpo.service;

import edu.uade.seminario.tpo.exception.BusinessException;
import edu.uade.seminario.tpo.model.Dosis;
import edu.uade.seminario.tpo.model.Paciente;

import java.util.List;

public interface PacienteService {

    List<Paciente> getPacientes();

    Paciente buscarPaciente(String dni);

    List<Dosis> buscarDosisPorPaciente(String dniPaciente) throws BusinessException;

}
