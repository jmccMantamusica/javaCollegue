package com.app.usuarios.microservicios.examenes.repository;

import com.app.usuarios.microservicios.commons.models.entity.Examen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ExamenRepository extends PagingAndSortingRepository<Examen, Long> {

    @Query("select e from Examen e where e.nombre like %?1%")
    public Optional<List<Examen>> findByNombre(String value);
}
