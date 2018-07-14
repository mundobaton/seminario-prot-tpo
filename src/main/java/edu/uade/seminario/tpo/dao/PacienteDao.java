package edu.uade.seminario.tpo.dao;

import edu.uade.seminario.tpo.model.Paciente;

import java.util.List;

public interface PacienteDao {

    List<Paciente> findAll();

    Paciente findByDni(String dni);

}
