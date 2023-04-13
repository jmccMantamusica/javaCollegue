package com.app.usuarios.microservicios.cursos.commons;

import com.app.usuarios.microservicios.commons.models.dto.AlumnoRequest;
import com.app.usuarios.microservicios.commons.models.entity.Alumno;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author jcagigas
 */
@Mapper(componentModel = "spring")
public interface AlumnoRequestMapper {

    AlumnoRequest EntityToDto(Alumno entity);

    List<AlumnoRequest> EntityListToDtoList(List<Alumno> list);

    @InheritInverseConfiguration
    Alumno DtoToEntity(AlumnoRequest srr);

    @InheritInverseConfiguration
    List<Alumno> DtoListToEntityList(List<AlumnoRequest> list);
}
