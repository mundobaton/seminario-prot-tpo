package edu.uade.seminario.tpo.service;

import edu.uade.seminario.tpo.exception.BusinessException;

public interface DosisService {

    void aplicarDosis(Long dosisId, String email) throws BusinessException;

}
