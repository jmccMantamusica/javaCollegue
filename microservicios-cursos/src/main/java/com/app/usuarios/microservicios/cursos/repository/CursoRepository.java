package com.app.usuarios.microservicios.cursos.repository;

import com.app.usuarios.microservicios.cursos.models.entity.Curso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CursoRepository extends CrudRepository<Curso, Long> {
    @Query("select c from Curso c join fetch c.alumnos a where a.id=?1")
    public Optional<Curso> findCursoByAlumnoId(Long id);
}
