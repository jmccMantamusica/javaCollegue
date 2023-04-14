package com.app.usuarios.microservicios.examenes.services;

import com.app.usuarios.microservicios.commons.models.entity.Asignatura;
import com.app.usuarios.microservicios.commons.models.entity.Examen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface ExamenService {

    public Iterable<Examen> findAll();

    public Optional<Examen> findById(Long id);

    public Page<Examen> findAll(Pageable pageable);

    public Examen save(Examen curso);

    public void deleteById(Long id);

    public Optional<List<Examen>> findByNombre(String value);

    public Iterable<Asignatura> findAllAsignaturas();

}
