package com.app.usuarios.microservicios.usuarios.repository;

import com.app.usuarios.microservicios.commons.models.entity.Alumno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface AlumnoRepository extends PagingAndSortingRepository<Alumno, Long> {

    @Query("select a from Alumno a where a.nombre like %?1% or a.apellido like %?1%")
    public Optional<List<Alumno>> findByNombreOrApellido(String value);
}
