package com.app.usuarios.microservicios.cursos.commons;

import com.app.usuarios.microservicios.cursos.models.dto.CursoRequest;
import com.app.usuarios.microservicios.cursos.models.entity.Curso;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author jcagigas
 */
@Mapper(componentModel = "spring")
public interface CursoRequestMapper{

    CursoRequest EntityToDto(Curso entity);

    List<CursoRequest> EntityListToDtoList(List<Curso> list);

    @InheritInverseConfiguration
    Curso DtoToEntity(CursoRequest srr);

    @InheritInverseConfiguration
    List<Curso> DtoListToEntityList(List<CursoRequest> list);
}
