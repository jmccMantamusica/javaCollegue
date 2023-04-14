package com.app.usuarios.microservicios.commons.models.entity;

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

    public Alumno generarAlumno(Alumno alumnoRequest){

        Alumno alumno = new Alumno();
        alumno.setNombre(alumnoRequest.getNombre());
        alumno.setApellido(alumnoRequest.getApellido());
        alumno.setEmail(alumnoRequest.getEmail());

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
