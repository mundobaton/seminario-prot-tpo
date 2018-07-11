package edu.uade.seminario.tpo.model;

import java.util.Date;
import java.util.List;

public class Indicacion {

    private Long codigoIndicacion;
    private Date fechaCreacion;
    private Paciente paciente;
    private String diagnostico;
    private Usuario medico;
    private EstadoIndicacion estado;
    private String observaciones;
    private List<ItemIndicacion> items;
    private Usuario farmaceutico;
    private Date fechaValidacion;
    private Date fechaRecepcion;
    private Usuario enfermero;

    public Long getCodigoIndicacion() {
        return codigoIndicacion;
    }

    public void setCodigoIndicacion(Long codigoIndicacion) {
        this.codigoIndicacion = codigoIndicacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Usuario getMedico() {
        return medico;
    }

    public void setMedico(Usuario medico) {
        this.medico = medico;
    }

    public EstadoIndicacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoIndicacion estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<ItemIndicacion> getItems() {
        return items;
    }

    public void setItems(List<ItemIndicacion> items) {
        this.items = items;
    }

    public Usuario getFarmaceutico() {
        return farmaceutico;
    }

    public void setFarmaceutico(Usuario farmaceutico) {
        this.farmaceutico = farmaceutico;
    }

    public Date getFechaValidacion() {
        return fechaValidacion;
    }

    public void setFechaValidacion(Date fechaValidacion) {
        this.fechaValidacion = fechaValidacion;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Usuario getEnfermero() {
        return enfermero;
    }

    public void setEnfermero(Usuario enfermero) {
        this.enfermero = enfermero;
    }
}
