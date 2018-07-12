package edu.uade.seminario.tpo.model;

import java.util.Date;

public class Dosis {

    private Long id;
    private Date fechaAplicacionPrevista;
    private Date fechaAplicacionEfectiva;
    private boolean aplicada;
    private int cantidad;
    private Usuario enfermero;

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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Usuario getEnfermero() {
        return enfermero;
    }

    public void setEnfermero(Usuario enfermero) {
        this.enfermero = enfermero;
    }
}
