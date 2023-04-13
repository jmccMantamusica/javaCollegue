package com.practice.app.usuarios.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CursoResponse {

    private Long id;
    private String nombre;
    private Date createAt;
}
