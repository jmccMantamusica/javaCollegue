package com.practice.app.usuarios.models.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Data
public class AlumnoResponseDto {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private Date createAt;
}
