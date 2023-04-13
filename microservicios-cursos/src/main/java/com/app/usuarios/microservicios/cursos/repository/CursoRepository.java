package com.app.usuarios.microservicios.cursos.repository;

import com.app.usuarios.microservicios.cursos.models.entity.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {
}
