package edu.uade.seminario.tpo.dao;

import edu.uade.seminario.tpo.model.Dosis;

public interface DosisDao {

    Dosis findById(Long dosisId);

    void save(Dosis dosis);

}
