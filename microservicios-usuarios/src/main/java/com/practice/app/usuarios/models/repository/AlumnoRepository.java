package com.practice.app.usuarios.models.repository;

import com.practice.app.usuarios.models.entity.Alumno;
import org.springframework.data.repository.CrudRepository;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
}
