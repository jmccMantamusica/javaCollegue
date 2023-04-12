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
public interface AlumnoResponseMapper {

    AlumnoResponse AlumnoToAlumnoResponse(Alumno alumno);

    List<AlumnoResponse> AlumnoListToAlumnoResponseList(List<Alumno> source);

    @InheritInverseConfiguration
    Alumno AlumnoResponseToAlumno(AlumnoResponse srr);

    @InheritInverseConfiguration
    List<Alumno> AlumnoResponseToAlumnoList(List<AlumnoResponse> source);
}
