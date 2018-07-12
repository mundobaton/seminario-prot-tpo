package edu.uade.seminario.tpo.entity;

import edu.uade.seminario.tpo.model.EstadoIndicacion;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "indicaciones")
public class IndicacionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "indicacion_id")
    private Long codigoIndicacion;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paciente_id")
    private PacienteEntity paciente;
    @Column(name = "diagnostico")
    private String diagnostico;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medico_id")
    private UsuarioEntity medico;
    @Column(name = "estado")
    @Enumerated(EnumType.ORDINAL)
    private EstadoIndicacion estado;
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(mappedBy = "indicacion")
    private List<ItemIndicacionEntity> items;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "farmaceutico_id")
    private UsuarioEntity farmaceutico;
    @Column(name = "fecha_validacion")
    private Date fechaValidacion;
    @Column(name = "fecha_recepcion")
    private Date fechaRecepcion;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "enfermero_id")
    private UsuarioEntity enfermero;

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

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public UsuarioEntity getMedico() {
        return medico;
    }

    public void setMedico(UsuarioEntity medico) {
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

    public List<ItemIndicacionEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemIndicacionEntity> items) {
        this.items = items;
    }

    public UsuarioEntity getFarmaceutico() {
        return farmaceutico;
    }

    public void setFarmaceutico(UsuarioEntity farmaceutico) {
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

    public UsuarioEntity getEnfermero() {
        return enfermero;
    }

    public void setEnfermero(UsuarioEntity enfermero) {
        this.enfermero = enfermero;
    }
}
