package edu.uade.seminario.tpo.service;

import edu.uade.seminario.tpo.dto.ItemIndicacionDTO;
import edu.uade.seminario.tpo.exception.BusinessException;
import edu.uade.seminario.tpo.model.Indicacion;

import java.util.List;

public interface IndicacionService {

    Indicacion generarIndicacion(String dniPaciente, String diagnostico) throws BusinessException;

    void agregarItems(Long indicacionId, List<ItemIndicacionDTO> items) throws BusinessException;

}
