package com.app.usuarios.microservicios.cursos.models.entity;

import com.app.usuarios.microservicios.commons.models.entity.Alumno;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cursos")
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Alumno> alumnos;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public Curso generarCurso(Curso curso) {

        Curso cursoFinal = new Curso();
        cursoFinal.setNombre(curso.getNombre());
        return cursoFinal;
    }

    public void addAlumno(Alumno alumno){
        this.alumnos.add(alumno);
    }

    public void removeAlumno(Alumno alumno){
        this.alumnos.remove(alumno);
    }
}
