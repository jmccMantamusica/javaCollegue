package com.app.usuarios.microservicios.examenes.services;

import com.app.usuarios.microservicios.commons.models.entity.Asignatura;
import com.app.usuarios.microservicios.commons.models.entity.Examen;
import com.app.usuarios.microservicios.examenes.repository.AsignaturaRepository;
import com.app.usuarios.microservicios.examenes.repository.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExamenServiceImpl implements ExamenService {

    @Autowired
    ExamenRepository repository;

    @Autowired
    AsignaturaRepository repositoryAsignatura;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Examen> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Examen> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Examen> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public Examen save(Examen curso) {
        return repository.save(curso);
    }
    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Examen>> findByNombre(String value) {
        return repository.findByNombre(value);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Asignatura> findAllAsignaturas() {
        return repositoryAsignatura.findAll();
    }
}
