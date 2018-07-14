package edu.uade.seminario.tpo.dao;

import edu.uade.seminario.tpo.model.Medicamento;

public interface MedicamentoDao {

    Medicamento findById(Long medicamentoId);

}
