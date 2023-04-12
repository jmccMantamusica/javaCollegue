package com.practice.app.usuarios.commons;

import com.practice.app.usuarios.models.dto.AlumnoRequest;
import com.practice.app.usuarios.models.dto.AlumnoResponse;
import com.practice.app.usuarios.models.entity.Alumno;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author jcagigas
 */
@Mapper(componentModel = "spring")
public interface ObjectMapper<E, S> {

    S EntityToDto(E entity);

    List<S> EntityListToDtoList(List<E> list);

    @InheritInverseConfiguration
    E DtoToEntity(S srr);

    @InheritInverseConfiguration
    List<E> DtoListToEntityList(List<S> list);
}
