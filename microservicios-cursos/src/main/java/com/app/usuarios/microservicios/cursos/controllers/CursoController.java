package com.app.usuarios.microservicios.cursos.controllers;

import com.app.usuarios.microservicios.commons.models.dto.AlumnoRequest;
import com.app.usuarios.microservicios.commons.models.dto.AlumnoResponse;
import com.app.usuarios.microservicios.commons.models.entity.Alumno;
import com.app.usuarios.microservicios.cursos.commons.AlumnoRequestMapper;
import com.app.usuarios.microservicios.cursos.commons.AlumnoResponseMapper;
import com.app.usuarios.microservicios.cursos.commons.CursoRequestMapper;
import com.app.usuarios.microservicios.cursos.commons.CursoResponseMapper;
import com.app.usuarios.microservicios.cursos.models.dto.CursoRequest;
import com.app.usuarios.microservicios.cursos.models.dto.CursoResponse;
import com.app.usuarios.microservicios.cursos.models.entity.Curso;
import com.app.usuarios.microservicios.cursos.services.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Curso API", description = "This APi serve all functionality for management Cursos")
@RestController
public class CursoController {

    @Autowired
    private CursoService service;

    @Autowired
    private CursoRequestMapper requestCurso;

    @Autowired
    private CursoResponseMapper responseCurso;

    @Autowired
    private AlumnoRequestMapper requestAlumno;

    @Autowired
    private AlumnoResponseMapper responseAlumno;

    @Operation(description = "Return all cursos bundled into Response", summary ="Return 204 if no data found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Exito: Listado de cursos."),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @Operation(description = "Return curso by id bundled into Response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Curso"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id){
        Optional<Curso> o = service.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responseCurso.EntityToDto(o.get()));
    }

    @Operation(description = "Return curso updated bundled into Response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Curso"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CursoRequest cursoRequest){
        Curso curso = requestCurso.DtoToEntity(cursoRequest);
        Curso cursoDB = service.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoDB);
    }

    @Operation(description = "Return curso updated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Curso updated"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody CursoRequest cursoRequest, @PathVariable Long id){

        Optional<Curso> o = service.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Curso cursoDB = o.get().generarCurso(cursoRequest);
        Curso curso = service.save(cursoDB);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseCurso.EntityToDto(curso));

    }

    @Operation(description = "Return code 204")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Curso deleted"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(description = "Return curso updated, alumno asigned")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Curso updated"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @PutMapping("/{id}/asignar-alumnos")
    public ResponseEntity<?> asignarAlumnos(@RequestBody List<AlumnoRequest> alumnoRequestList, @PathVariable Long id){
        Optional<Curso> o = this.service.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Curso cursoDb = o.get();
        List<Alumno> alumnoList = requestAlumno.DtoListToEntityList(alumnoRequestList);
        alumnoList.forEach(cursoDb::addAlumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseCurso.EntityToDto(cursoDb));
    }

    @Operation(description = "Return curso updated, alumno deleted")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Curso updated"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @PutMapping("/{id}/eliminar-alumno")
    public ResponseEntity<?> eliminarAlumno(@RequestBody AlumnoRequest alumnoRequest, @PathVariable Long id){
        Optional<Curso> o = this.service.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Curso cursoDb = o.get();
        cursoDb.removeAlumno(requestAlumno.DtoToEntity(alumnoRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseCurso.EntityToDto(cursoDb));
    }
}
