package edu.uade.seminario.tpo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "item_indicaciones")
public class ItemIndicacionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_indicacion_id")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medicamento_id")
    private MedicamentoEntity medicamento;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "frecuencia")
    private double frecuencia;
    @OneToMany(mappedBy = "itemIndicacion", cascade = CascadeType.ALL)
    private List<DosisEntity> dosis;
    @Column(name = "fecha_recepcion")
    private Date fechaRecepcion;
    @ManyToOne
    @JoinColumn(name = "indicacion_id")
    private IndicacionEntity indicacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MedicamentoEntity getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(MedicamentoEntity medicamento) {
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

    public List<DosisEntity> getDosis() {
        return dosis;
    }

    public void setDosis(List<DosisEntity> dosis) {
        this.dosis = dosis;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public IndicacionEntity getIndicacion() {
        return indicacion;
    }

    public void setIndicacion(IndicacionEntity indicacion) {
        this.indicacion = indicacion;
    }
}
