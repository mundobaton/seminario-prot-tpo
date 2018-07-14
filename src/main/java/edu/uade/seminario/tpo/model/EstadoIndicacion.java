package edu.uade.seminario.tpo.model;

import java.util.Arrays;

public enum EstadoIndicacion {

    PENDIENTE,
    RECHAZADO,
    ARCHIVADO,
    VALIDADO,
    ENVIADO,
    RECIBIDO;

    public static EstadoIndicacion getByName(String name) {
        return valueOf(name.toUpperCase());
    }
}
