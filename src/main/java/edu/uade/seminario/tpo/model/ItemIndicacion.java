package edu.uade.seminario.tpo.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ItemIndicacion {

    private Long id;
    private Medicamento medicamento;
    private int cantidad;
    private double frecuencia; //expresado en horas, no puede superar 24
    private List<Dosis> dosis;
    private Date fechaRecepcion;
    private Indicacion indicacion;

    public ItemIndicacion() {

    }

    public ItemIndicacion(Medicamento medicamento, int cantidad, double frecuencia, Indicacion indicacion) {
        this.medicamento = medicamento;
        this.cantidad = cantidad;
        this.frecuencia = frecuencia;
        this.indicacion = indicacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(double frecuencia) {
        this.frecuencia = frecuencia;
    }

    public List<Dosis> getDosis() {
        return dosis;
    }

    public void setDosis(List<Dosis> dosis) {
        this.dosis = dosis;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Indicacion getIndicacion() {
        return indicacion;
    }

    public void setIndicacion(Indicacion indicacion) {
        this.indicacion = indicacion;
    }

    public boolean contieneStock() {
        return obtenerCantidadTotal() <= medicamento.getStock();
    }

    private int obtenerCantidadTotal() {
        return (int) ((1 / frecuencia) * 24 * cantidad);
    }

    public void descontarStockMedicamento() {
        medicamento.setStock(medicamento.getStock() - obtenerCantidadTotal());
    }

    public void generarDosis() {
        this.dosis = new ArrayList<>();
        Calendar now = Calendar.getInstance();
        for (int i = 0; i < obtenerCantidadTotal(); i++) {
            Dosis dosis = new Dosis(now.getTime(), this);
            this.dosis.add(dosis);

            now.add(Calendar.MINUTE, (int) frecuencia * 60);
        }
    }
}
