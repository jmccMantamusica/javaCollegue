package com.app.usuarios.microservicios.cursos.services;

import com.app.usuarios.microservicios.cursos.repository.CursoRepository;
import com.app.usuarios.microservicios.cursos.models.entity.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService{

    @Autowired
    CursoRepository cursoRepository;


    @Override
    @Transactional(readOnly = true)
    public Iterable<Curso> findAll() {
        return cursoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    @Transactional
    public Curso save(Curso alumno) {
        return cursoRepository.save(alumno);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        cursoRepository.deleteById(id);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findCursoByAlumnoId(Long id) {
        return cursoRepository.findCursoByAlumnoId(id);
    }
}
