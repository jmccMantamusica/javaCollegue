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
public interface AlumnoRequestMapper {

    Alumno AlumnoRequestToAlumno(AlumnoRequest alumno);

    List<AlumnoRequest> AlumnoListToAlumnoRequestList(List<Alumno> source);

    @InheritInverseConfiguration
    AlumnoRequest AlumnoRequestToAlumno(Alumno srr);

}
