package com.app.usuarios.microservicios.examenes.repository;

import com.app.usuarios.microservicios.commons.models.entity.Asignatura;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AsignaturaRepository extends PagingAndSortingRepository<Asignatura, Long> {
}
