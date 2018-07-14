package edu.uade.seminario.tpo.service;

import edu.uade.seminario.tpo.dto.ItemIndicacionDTO;
import edu.uade.seminario.tpo.exception.BusinessException;
import edu.uade.seminario.tpo.model.EstadoIndicacion;
import edu.uade.seminario.tpo.model.Indicacion;

import java.util.List;

public interface IndicacionService {

    Indicacion generarIndicacion(String dniPaciente, String diagnostico) throws BusinessException;

    void agregarItems(Long indicacionId, List<ItemIndicacionDTO> items) throws BusinessException;

    void finalizarCargaItems(Long indicacionId, String email) throws BusinessException;

    List<Indicacion> buscarPorEstado(EstadoIndicacion estado);

    Indicacion modificarRechazada(Long indicacionId) throws BusinessException;

    Indicacion findIndicacion(Long indicacionId) throws BusinessException;

    void validarIndicacion(Long indicacionId, String email) throws BusinessException;

}
