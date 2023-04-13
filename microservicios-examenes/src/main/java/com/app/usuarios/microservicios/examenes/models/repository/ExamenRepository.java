package com.app.usuarios.microservicios.examenes.models.repository;

import com.app.usuarios.microservicios.examenes.models.entity.Examen;
import org.springframework.data.repository.CrudRepository;

public interface ExamenRepository extends CrudRepository<Examen, Long> {
}
