package com.practice.app.usuarios.services;

import com.practice.app.usuarios.models.dto.AlumnoRequestDto;
import com.practice.app.usuarios.models.dto.AlumnoResponseDto;
import com.practice.app.usuarios.models.entity.Alumno;
import org.mapstruct.Mapper;

@Mapper
public interface AlumnoConverter {

    Alumno convertAlumnoRequestDtotoAlumno(AlumnoRequestDto alumnoRequestDto);

    AlumnoResponseDto convertAlumnoToAlumnoResponseDto(Alumno alumno);
}
