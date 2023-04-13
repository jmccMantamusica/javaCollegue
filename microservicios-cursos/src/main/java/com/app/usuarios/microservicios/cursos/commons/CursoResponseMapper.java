package com.app.usuarios.microservicios.cursos.commons;

import com.app.usuarios.microservicios.cursos.models.dto.CursoResponse;
import com.app.usuarios.microservicios.cursos.models.entity.Curso;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author jcagigas
 */
@Mapper(componentModel = "spring")
public interface CursoResponseMapper {

    CursoResponse EntityToDto(Curso entity);

    List<CursoResponse> EntityListToDtoList(List<Curso> list);

    @InheritInverseConfiguration
    Curso DtoToEntity(CursoResponse entity);

    @InheritInverseConfiguration
    List<Curso> DtoListToEntityList(List<CursoResponse> list);
}
