package edu.uade.seminario.tpo.model;

import java.util.Date;

public class Dosis {

    private Long id;
    private Date fechaAplicacionPrevista;
    private Date fechaAplicacionEfectiva;
    private boolean aplicada;
    private Usuario enfermero;
    private ItemIndicacion itemIndicacion;
    private Paciente paciente;

    public Dosis() {

    }

    public Dosis(Date fechaPrevista, ItemIndicacion itemIndicacion) {
        this.fechaAplicacionPrevista = fechaPrevista;
        this.itemIndicacion = itemIndicacion;
        this.paciente = itemIndicacion.getIndicacion().getPaciente();
        this.aplicada = false;
    }

    public void aplicar(Usuario enfermero) {
        this.enfermero = enfermero;
        this.fechaAplicacionEfectiva = new Date();
        this.aplicada = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaAplicacionPrevista() {
        return fechaAplicacionPrevista;
    }

    public void setFechaAplicacionPrevista(Date fechaAplicacionPrevista) {
        this.fechaAplicacionPrevista = fechaAplicacionPrevista;
    }

    public Date getFechaAplicacionEfectiva() {
        return fechaAplicacionEfectiva;
    }

    public void setFechaAplicacionEfectiva(Date fechaAplicacionEfectiva) {
        this.fechaAplicacionEfectiva = fechaAplicacionEfectiva;
    }

    public boolean isAplicada() {
        return aplicada;
    }

    public void setAplicada(boolean aplicada) {
        this.aplicada = aplicada;
    }

    public Usuario getEnfermero() {
        return enfermero;
    }

    public void setEnfermero(Usuario enfermero) {
        this.enfermero = enfermero;
    }

    public ItemIndicacion getItemIndicacion() {
        return itemIndicacion;
    }

    public void setItemIndicacion(ItemIndicacion itemIndicacion) {
        this.itemIndicacion = itemIndicacion;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
