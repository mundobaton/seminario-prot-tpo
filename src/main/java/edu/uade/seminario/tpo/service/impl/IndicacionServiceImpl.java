package edu.uade.seminario.tpo.service.impl;

import edu.uade.seminario.tpo.dao.IndicacionDao;
import edu.uade.seminario.tpo.dao.MedicamentoDao;
import edu.uade.seminario.tpo.dao.UsuarioDao;
import edu.uade.seminario.tpo.dto.ItemIndicacionDTO;
import edu.uade.seminario.tpo.exception.BusinessException;
import edu.uade.seminario.tpo.model.EstadoIndicacion;
import edu.uade.seminario.tpo.model.Indicacion;
import edu.uade.seminario.tpo.model.ItemIndicacion;
import edu.uade.seminario.tpo.model.Medicamento;
import edu.uade.seminario.tpo.model.Paciente;
import edu.uade.seminario.tpo.model.Rol;
import edu.uade.seminario.tpo.model.Usuario;
import edu.uade.seminario.tpo.service.IndicacionService;
import edu.uade.seminario.tpo.service.PacienteService;
import org.eclipse.jetty.http.HttpStatus;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class IndicacionServiceImpl implements IndicacionService {

    @Inject
    private PacienteService pacienteService;
    @Inject
    private IndicacionDao indicacionDao;
    @Inject
    private MedicamentoDao medicamentoDao;
    @Inject
    private UsuarioDao usuarioDao;

    @Override
    public Indicacion generarIndicacion(String dniPaciente, String diagnostico) throws BusinessException {
        Paciente paciente = pacienteService.buscarPaciente(dniPaciente);
        if (paciente == null) {
            throw new BusinessException("El paciente con dni '" + dniPaciente + "' no existe", HttpStatus.NOT_FOUND_404);
        }
        Indicacion indicacion = new Indicacion(paciente, diagnostico);
        indicacion = indicacionDao.save(indicacion);
        return indicacion;
    }

    @Override
    public void agregarItems(Long indicacionId, List<ItemIndicacionDTO> items) throws BusinessException {
        Indicacion indicacion = indicacionDao.findById(indicacionId);
        if (indicacion == null) {
            throw new BusinessException("La indicacion con id '" + indicacionId.toString() + "' no existe", HttpStatus.NOT_FOUND_404);
        }
        List<ItemIndicacion> itemIndicaciones = new ArrayList<>();
        for (ItemIndicacionDTO item : items) {
            Medicamento medicamento = medicamentoDao.findById(item.getMedicamentoId());
            if (medicamento == null) {
                throw new BusinessException("El medicamento con id '" + item.getMedicamentoId() + "' no existe");
            }
            ItemIndicacion ii = new ItemIndicacion(medicamento, item.getCantidad(), item.getFrecuencia(), indicacion);
            itemIndicaciones.add(ii);
        }
        indicacion.setItems(itemIndicaciones);
        indicacionDao.save(indicacion);
    }

    @Override
    public void finalizarCargaItems(Long indicacionId, String email) throws BusinessException {
        Indicacion indicacion = indicacionDao.findById(indicacionId);
        if (indicacion == null) {
            throw new BusinessException("La indicacion con id '" + indicacionId.toString() + "' no existe", HttpStatus.NOT_FOUND_404);
        }
        Usuario medico = usuarioDao.findByEmailAndRole(email, Rol.MEDICO);
        if (medico == null) {
            throw new BusinessException("No se encontró el medico con email '" + email + "'", HttpStatus.NOT_FOUND_404);
        }
        indicacion.finalizarCargaItems(medico);
        indicacionDao.save(indicacion);
    }

    @Override
    public List<Indicacion> buscarPorEstado(EstadoIndicacion estado) {
        return indicacionDao.findByEstado(estado);
    }
}
