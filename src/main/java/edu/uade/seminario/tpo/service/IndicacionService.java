package edu.uade.seminario.tpo.service;

import edu.uade.seminario.tpo.dto.ItemIndicacionDTO;
import edu.uade.seminario.tpo.exception.BusinessException;
import edu.uade.seminario.tpo.model.EstadoIndicacion;
import edu.uade.seminario.tpo.model.Indicacion;
import edu.uade.seminario.tpo.model.Medicamento;

import java.util.List;

public interface IndicacionService {

    Indicacion generarIndicacion(String dniPaciente, String diagnostico) throws BusinessException;

    void agregarItems(Long indicacionId, List<ItemIndicacionDTO> items) throws BusinessException;

    void finalizarCargaItems(Long indicacionId, String email) throws BusinessException;

    List<Indicacion> buscarPorEstado(EstadoIndicacion estado);

    Indicacion modificarRechazada(Long indicacionId) throws BusinessException;

    Indicacion findIndicacion(Long indicacionId) throws BusinessException;

    void validarIndicacion(Long indicacionId, String email) throws BusinessException;

    void enviarIndicacion(Long indicacionId) throws BusinessException;

    void aceptarIndicacion(Long indicacionId, String email) throws BusinessException;

    List<Indicacion> buscarPorPaciente(String dni);

    List<Medicamento> obtenerMedicamentos();

    void rechazarIndicacion(Long indicacionId) throws BusinessException;

}
