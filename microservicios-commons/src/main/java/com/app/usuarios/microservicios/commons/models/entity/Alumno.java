package com.app.usuarios.microservicios.commons.models.entity;

import com.app.usuarios.microservicios.commons.models.dto.AlumnoRequest;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

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

    public Alumno generarAlumno(AlumnoRequest alumnoRequestDto){

        Alumno alumno = new Alumno();
        alumno.setNombre(alumnoRequestDto.getNombre());
        alumno.setApellido(alumnoRequestDto.getApellido());
        alumno.setEmail(alumnoRequestDto.getEmail());

        return alumno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof Alumno)){
            return false;
        }
        Alumno a = (Alumno) o;
        return this.id != null && this.id.equals(a.getId());
    }

}
