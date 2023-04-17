package com.app.usuarios.microservicios.respuestas.models.entity;

import com.app.usuarios.microservicios.commons.models.entity.Alumno;
import com.app.usuarios.microservicios.commons.models.entity.Examen;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "respuestas")
@Data
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String texto;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Alumno alunmno;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private Examen examen;
}
