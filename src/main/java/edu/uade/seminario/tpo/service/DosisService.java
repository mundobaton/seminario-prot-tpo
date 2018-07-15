package edu.uade.seminario.tpo.service;

import edu.uade.seminario.tpo.exception.BusinessException;
import edu.uade.seminario.tpo.model.Dosis;

import java.util.List;

public interface DosisService {

    void aplicarDosis(Long dosisId, String email) throws BusinessException;

    List<Dosis> obtenerDosis();

}
