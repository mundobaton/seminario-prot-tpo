package edu.uade.seminario.tpo.service.impl;

import edu.uade.seminario.tpo.dao.PacienteDao;
import edu.uade.seminario.tpo.model.Paciente;
import edu.uade.seminario.tpo.service.PacienteService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class PacienteServiceImpl implements PacienteService {

    @Inject
    private PacienteDao pacienteDao;

    @Override
    public List<Paciente> getPacientes() {
        return pacienteDao.findAll();
    }

    @Override
    public Paciente buscarPaciente(String dni) {
        return pacienteDao.findByDni(dni);
    }
}
