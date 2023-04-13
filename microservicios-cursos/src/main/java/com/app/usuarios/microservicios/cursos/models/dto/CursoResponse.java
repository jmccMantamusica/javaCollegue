package com.app.usuarios.microservicios.cursos.models.dto;

import com.app.usuarios.microservicios.commons.models.entity.Alumno;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CursoResponse {

    private Long id;
    private String nombre;
    private List<Alumno> alumnoList;
    private Date createAt;
}
