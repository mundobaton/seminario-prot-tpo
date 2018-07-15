package edu.uade.seminario.tpo.dao;

import edu.uade.seminario.tpo.model.Medicamento;

import java.util.List;

public interface MedicamentoDao {

    Medicamento findById(Long medicamentoId);

    List<Medicamento> findAll();

}
