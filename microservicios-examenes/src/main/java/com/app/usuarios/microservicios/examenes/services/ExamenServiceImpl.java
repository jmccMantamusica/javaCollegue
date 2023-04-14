package com.app.usuarios.microservicios.examenes.services;

import com.app.usuarios.microservicios.commons.models.entity.Examen;
import com.app.usuarios.microservicios.examenes.models.repository.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ExamenServiceImpl implements ExamenService {

    @Autowired
    ExamenRepository repository;

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
    @Transactional
    public Examen save(Examen curso) {
        return repository.save(curso);
    }
    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);

    }
}
