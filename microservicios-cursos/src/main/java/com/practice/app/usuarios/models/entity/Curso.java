package com.practice.app.usuarios.models.entity;

import com.practice.app.usuarios.models.dto.CursoRequest;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cursos")
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public Curso generarCurso(CursoRequest cursoRequest) {

        Curso curso = new Curso();
        curso.setNombre(cursoRequest.getNombre());
        return curso;
    }
}
