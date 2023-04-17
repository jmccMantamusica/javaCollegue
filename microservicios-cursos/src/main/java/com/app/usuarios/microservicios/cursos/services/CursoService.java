package com.app.usuarios.microservicios.cursos.services;

import com.app.usuarios.microservicios.cursos.models.entity.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface CursoService {

    public Iterable<Curso> findAll();

    public Page<Curso> findAll(Pageable pageable);

    public Optional<Curso> findById(Long id);

    public Curso save(Curso alumno);

    public void deleteById(Long id);

    public Optional<Curso> findCursoByAlumnoId(Long id);

    public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId);
}
