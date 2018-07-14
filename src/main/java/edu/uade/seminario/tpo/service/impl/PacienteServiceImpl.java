package edu.uade.seminario.tpo.service.impl;

import edu.uade.seminario.tpo.dao.PacienteDao;
import edu.uade.seminario.tpo.exception.BusinessException;
import edu.uade.seminario.tpo.model.Dosis;
import edu.uade.seminario.tpo.model.EstadoIndicacion;
import edu.uade.seminario.tpo.model.Indicacion;
import edu.uade.seminario.tpo.model.ItemIndicacion;
import edu.uade.seminario.tpo.model.Paciente;
import edu.uade.seminario.tpo.service.IndicacionService;
import edu.uade.seminario.tpo.service.PacienteService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Singleton
public class PacienteServiceImpl implements PacienteService {

    @Inject
    private PacienteDao pacienteDao;
    @Inject
    private IndicacionService indicacionService;

    @Override
    public List<Paciente> getPacientes() {
        return pacienteDao.findAll();
    }

    @Override
    public Paciente buscarPaciente(String dni) {
        return pacienteDao.findByDni(dni);
    }

    @Override
    public List<Dosis> buscarDosisPorPaciente(String dniPaciente) throws BusinessException {
        List<Dosis> dosis = new ArrayList<>();
        List<Indicacion> indicaciones = indicacionService.buscarPorPaciente(dniPaciente);
        for (Indicacion indicacion : indicaciones) {
            if (indicacion.getEstado() == EstadoIndicacion.RECIBIDO) {
                for (ItemIndicacion item : indicacion.getItems()) {
                    for (Dosis d : item.getDosis()) {
                        if (!d.isAplicada()) {
                            dosis.add(d);
                        }
                    }
                }
            }
        }

        dosis.sort(Comparator.comparing(Dosis::getFechaAplicacionPrevista));

        return dosis;
    }
}
