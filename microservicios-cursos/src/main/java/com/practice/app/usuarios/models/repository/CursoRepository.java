package com.practice.app.usuarios.models.repository;

import com.practice.app.usuarios.models.entity.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {
}
