package edu.uade.seminario.tpo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "dosis")
public class DosisEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dosis_id")
    private Long id;
    @Column(name = "fecha_aplicacion_prevista")
    private Date fechaAplicacionPrevista;
    @Column(name = "fecha_aplicacion_efectiva")
    private Date fechaAplicacionEfectiva;
    @Column(name = "aplicada")
    private boolean aplicada;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "enfermero_id")
    private UsuarioEntity enfermero;
    @ManyToOne
    @JoinColumn(name="item_indicacion_id")
    private ItemIndicacionEntity itemIndicacion;

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

    public UsuarioEntity getEnfermero() {
        return enfermero;
    }

    public void setEnfermero(UsuarioEntity enfermero) {
        this.enfermero = enfermero;
    }

    public ItemIndicacionEntity getItemIndicacion() {
        return itemIndicacion;
    }

    public void setItemIndicacion(ItemIndicacionEntity itemIndicacion) {
        this.itemIndicacion = itemIndicacion;
    }
}
