package com.app.usuarios.microservicios.usuarios.services;

import com.app.usuarios.microservicios.commons.models.entity.Alumno;

import java.util.List;
import java.util.Optional;

public interface AlumnoService {

    public Iterable<Alumno> findAll();

    public Optional<Alumno> findById(Long id);

    public Alumno save(Alumno alumno);

    public void deleteById(Long id);

    public Optional<List<Alumno>> findByNombreOrApellido(String value);
}
