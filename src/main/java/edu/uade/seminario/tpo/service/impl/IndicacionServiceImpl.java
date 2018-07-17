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
        Indicacion indicacion = this.findIndicacion(indicacionId);
        List<ItemIndicacion> itemIndicaciones = new ArrayList<>();
        for (ItemIndicacionDTO item : items) {
            Medicamento medicamento = medicamentoDao.findById(item.getMedicamentoId());
            if (medicamento == null) {
                throw new BusinessException("El medicamento con id '" + item.getMedicamentoId() + "' no existe", HttpStatus.NOT_FOUND_404);
            }
            if (item.getFrecuencia() > 24) {
                throw new BusinessException("La frecuencia debe estar comprendida entre (0, 24]", HttpStatus.BAD_REQUEST_400);
            }
            ItemIndicacion ii = new ItemIndicacion(medicamento, item.getCantidad(), item.getFrecuencia(), indicacion);
            itemIndicaciones.add(ii);
        }
        indicacion.setItems(itemIndicaciones);
        indicacionDao.save(indicacion);
    }

    @Override
    public void finalizarCargaItems(Long indicacionId, String email) throws BusinessException {
        Indicacion indicacion = this.findIndicacion(indicacionId);
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

    @Override
    public Indicacion modificarRechazada(Long indicacionId) throws BusinessException {
        Indicacion indicacion = this.findIndicacion(indicacionId);
        if (indicacion.getEstado() != EstadoIndicacion.RECHAZADO) {
            throw new BusinessException("La indicacion no se encuentra en estado 'RECHAZADO'", HttpStatus.BAD_REQUEST_400);
        }

        Indicacion nuevaIndicacion = Indicacion.clone(indicacion);
        nuevaIndicacion = indicacionDao.save(nuevaIndicacion);

        indicacion.archivar();
        indicacionDao.save(indicacion);

        return nuevaIndicacion;
    }

    @Override
    public Indicacion findIndicacion(Long indicacionId) throws BusinessException {
        Indicacion indicacion = indicacionDao.findById(indicacionId);
        if (indicacion == null) {
            throw new BusinessException("La indicacion con id '" + indicacionId.toString() + "' no existe", HttpStatus.NOT_FOUND_404);
        }
        return indicacion;
    }

    @Override
    public void validarIndicacion(Long indicacionId, String email) throws BusinessException {
        Indicacion indicacion = this.findIndicacion(indicacionId);
        Usuario farmaceutico = usuarioDao.findByEmailAndRole(email, Rol.FARMACEUTICO);
        if (farmaceutico == null) {
            throw new BusinessException("No se encontró el farmaceutico con email '" + email + "'", HttpStatus.NOT_FOUND_404);
        }
        indicacion.validar(farmaceutico);
        indicacionDao.save(indicacion);
    }

    @Override
    public void enviarIndicacion(Long indicacionId) throws BusinessException {
        Indicacion indicacion = this.findIndicacion(indicacionId);
        if (indicacion.completaStock()) {
            indicacion.enviar();
        } else {
            indicacion.rechazar("Falta de stock", null);
        }
        indicacionDao.save(indicacion);
    }

    @Override
    public void aceptarIndicacion(Long indicacionId, String email) throws BusinessException {
        Indicacion indicacion = this.findIndicacion(indicacionId);
        Usuario enfermero = usuarioDao.findByEmailAndRole(email, Rol.ENFERMERO);
        if (enfermero == null) {
            throw new BusinessException("No se encontró el enfermero con email '" + email + "'", HttpStatus.NOT_FOUND_404);
        }
        indicacion.recibir(enfermero);
        indicacionDao.save(indicacion);
    }

    @Override
    public List<Indicacion> buscarPorPaciente(String dni) {
        return indicacionDao.findByDniPaciente(dni);
    }

    @Override
    public List<Medicamento> obtenerMedicamentos() {
        return medicamentoDao.findAll();
    }

    @Override
    public void rechazarIndicacion(Long indicacionId, String email, String motivo) throws BusinessException {
        Indicacion indicacion = this.findIndicacion(indicacionId);
        Usuario farmaceutico = usuarioDao.findByEmailAndRole(email, Rol.FARMACEUTICO);
        if (farmaceutico == null) {
            throw new BusinessException("No se encontró el farmaceutico con email '" + email + "'", HttpStatus.NOT_FOUND_404);
        }
        indicacion.rechazar(motivo, farmaceutico);
        indicacionDao.save(indicacion);
    }
}
