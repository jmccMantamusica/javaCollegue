package com.practice.app.usuarios.controllers;

import com.practice.app.usuarios.models.dto.AlumnoRequestDto;
import com.practice.app.usuarios.models.entity.Alumno;
import com.practice.app.usuarios.services.AlumnoConverter;
import com.practice.app.usuarios.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AlumnoController {

    @Autowired
    private AlumnoService service;

    @Autowired
    private AlumnoConverter converter;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok().body(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id){
        Optional<Alumno> o = service.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(converter.convertAlumnoToAlumnoResponseDto(o.get()));
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody AlumnoRequestDto alumnoRequestDto){
        Alumno alumno = converter.convertAlumnoRequestDtotoAlumno(alumnoRequestDto);
        Alumno alumnoDB = service.save(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDB);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody AlumnoRequestDto alumnoRequestDto, @PathVariable Long id){

        Optional<Alumno> o = service.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Alumno alumnoDB = o.get();
        alumnoDB.setNombre(alumnoRequestDto.getNombre());
        alumnoDB.setApellido(alumnoRequestDto.getApellido());
        alumnoDB.setEmail(alumnoRequestDto.getEmail());

        Alumno alumno = service.save(alumnoDB);

        return ResponseEntity.status(HttpStatus.CREATED).body(converter.convertAlumnoToAlumnoResponseDto(alumno));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
