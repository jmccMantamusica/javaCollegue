package com.app.usuarios.microservicios.usuarios.repository;

import com.app.usuarios.microservicios.commons.models.entity.Alumno;
import org.springframework.data.repository.CrudRepository;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
}
