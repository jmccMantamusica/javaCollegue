package com.app.usuarios.microservicios.examenes.services;

import com.app.usuarios.microservicios.commons.models.entity.Examen;

import java.util.Optional;

public interface ExamenService {

    public Iterable<Examen> findAll();

    public Optional<Examen> findById(Long id);

    public Examen save(Examen curso);

    public void deleteById(Long id);

}
