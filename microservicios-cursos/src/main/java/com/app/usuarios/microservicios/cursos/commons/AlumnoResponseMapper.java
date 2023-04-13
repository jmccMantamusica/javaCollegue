package com.app.usuarios.microservicios.cursos.commons;

import com.app.usuarios.microservicios.commons.models.dto.AlumnoResponse;
import com.app.usuarios.microservicios.commons.models.entity.Alumno;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author jcagigas
 */
@Mapper(componentModel = "spring")
public interface AlumnoResponseMapper {

    AlumnoResponse EntityToDto(Alumno entity);

    List<AlumnoResponse> EntityListToDtoList(List<Alumno> list);

    @InheritInverseConfiguration
    Alumno DtoToEntity(AlumnoResponse entity);

    @InheritInverseConfiguration
    List<Alumno> DtoListToEntityList(List<AlumnoResponse> list);
}
