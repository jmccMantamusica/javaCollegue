package com.practice.app.usuarios.models.entity;

import com.practice.app.usuarios.models.dto.AlumnoRequestDto;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "alumnos")
@Data
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public Alumno generarAlumno(AlumnoRequestDto alumnoRequestDto){

        Alumno alumno = new Alumno();
        alumno.setNombre(alumnoRequestDto.getNombre());
        alumno.setApellido(alumnoRequestDto.getApellido());
        alumno.setEmail(alumnoRequestDto.getEmail());

        return alumno;
    }
}
