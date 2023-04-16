package com.app.usuarios.microservicios.commons.models.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "alumnos")
@Data
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotEmpty
    @Email
    private String email;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Lob
    @JsonIgnore
    private byte[] foto;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public Integer getFotoHashCode(){
        return (this.foto != null) ? this.foto.hashCode(): null;
    }

    public Alumno generarAlumno(Alumno alumnoRequest){

        this.setNombre(alumnoRequest.getNombre());
        this.setApellido(alumnoRequest.getApellido());
        this.setEmail(alumnoRequest.getEmail());

        return this;
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
