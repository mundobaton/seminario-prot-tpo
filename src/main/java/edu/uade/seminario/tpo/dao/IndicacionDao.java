package edu.uade.seminario.tpo.dao;

import edu.uade.seminario.tpo.model.EstadoIndicacion;
import edu.uade.seminario.tpo.model.Indicacion;

import java.util.List;

public interface IndicacionDao {

    Indicacion save(Indicacion indicacion);

    Indicacion findById(Long indicacionId);

    List<Indicacion> findByEstado(EstadoIndicacion estadoIndicacion);

}
