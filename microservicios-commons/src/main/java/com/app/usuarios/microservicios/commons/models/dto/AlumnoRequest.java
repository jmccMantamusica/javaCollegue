package com.app.usuarios.microservicios.commons.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AlumnoRequest {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private Date createAt;

}
