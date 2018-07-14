package edu.uade.seminario.tpo.dao;

import edu.uade.seminario.tpo.model.Indicacion;

public interface IndicacionDao {

    Indicacion save(Indicacion indicacion);

    Indicacion findById(Long indicacionId);

}
