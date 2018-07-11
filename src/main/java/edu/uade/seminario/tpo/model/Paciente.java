package edu.uade.seminario.tpo.model;

import java.util.Date;

public class Paciente {

    private String dni;
    private String nombre;
    private String apellido;
    private String obraSocial;
    private boolean internado;
    private Date fechaNacimiento;
    private Long numAfiliado;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public boolean isInternado() {
        return internado;
    }

    public void setInternado(boolean internado) {
        this.internado = internado;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getNumAfiliado() {
        return numAfiliado;
    }

    public void setNumAfiliado(Long numAfiliado) {
        this.numAfiliado = numAfiliado;
    }
}
