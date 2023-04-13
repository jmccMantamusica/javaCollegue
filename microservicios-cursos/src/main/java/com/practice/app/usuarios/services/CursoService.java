package com.practice.app.usuarios.services;

import com.practice.app.usuarios.models.entity.Curso;

import java.util.Optional;

public interface CursoService {

    public Iterable<Curso> findAll();

    public Optional<Curso> findById(Long id);

    public Curso save(Curso alumno);

    public void deleteById(Long id);
}
