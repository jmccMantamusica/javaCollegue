package com.practice.app.usuarios.models.dto;

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
