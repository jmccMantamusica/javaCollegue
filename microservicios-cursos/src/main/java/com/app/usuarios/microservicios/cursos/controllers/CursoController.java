package com.app.usuarios.microservicios.cursos.controllers;

import com.app.usuarios.microservicios.commons.models.entity.Alumno;
import com.app.usuarios.microservicios.commons.models.entity.Examen;
import com.app.usuarios.microservicios.cursos.models.entity.Curso;
import com.app.usuarios.microservicios.cursos.services.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Curso API", description = "This APi serve all functionality for management Cursos")
@RestController
public class CursoController {

    @Autowired
    private CursoService service;

    @Operation(description = "Return all page of cursos bundled into Response", summary ="Return 204 if no data found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Exito: Páginas."),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("/pagina")
    public ResponseEntity<?> paginas(Pageable pageable){
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

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
        return ResponseEntity.ok(o.get());
    }

    @Operation(description = "Return curso by Alumno id bundled into Response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Curso"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("/alumno/{id}")
    public ResponseEntity<?> verPorAlumnoId(@PathVariable Long id){
        Optional<Curso> o = service.findCursoByAlumnoId(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        if(o.get() != null){
            List<Long> examenesIds = (List<Long>) service.obtenerExamenesIdsConRespuestasAlumno(id);
            List<Examen> examenList = o.get().getExamenes().stream().map(examen -> {
                if(examenesIds.contains(examen.getId())){
                    examen.setRespondido(true);
                }
                return examen;
            }).collect(Collectors.toList());

            o.get().setExamenes(examenList);
        }
        return ResponseEntity.ok(o.get());
    }

    @Operation(description = "Return curso updated bundled into Response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Curso"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Curso cursoRequest, BindingResult result){
        if(result.hasErrors()){
            return this.validar(result);
        }
        Curso cursoDB = service.save(cursoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoDB);
    }

    @Operation(description = "Return curso updated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Curso updated"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Curso cursoRequest, BindingResult result,
                                    @PathVariable Long id){

        if(result.hasErrors()){
            return this.validar(result);
        }

        Optional<Curso> o = service.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Curso cursoDB = o.get().generarCurso(cursoRequest);
        Curso curso = service.save(cursoDB);

        return ResponseEntity.status(HttpStatus.CREATED).body(curso);

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
    public ResponseEntity<?> asignarAlumnos(@Valid @RequestBody List<Alumno> alumnoRequestList,
                                            @PathVariable Long id){
        Optional<Curso> o = this.service.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Curso cursoDb = o.get();
        alumnoRequestList.forEach(cursoDb::addAlumno);
        Curso cursoFinal = service.save(cursoDb);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoFinal);
    }

    @Operation(description = "Return curso updated, alumno deleted")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Curso updated"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @PutMapping("/{id}/eliminar-alumno")
    public ResponseEntity<?> eliminarAlumno(@Valid @RequestBody Alumno alumnoRequest, BindingResult result,
                                            @PathVariable Long id){
        if(result.hasErrors()){
            return this.validar(result);
        }

        Optional<Curso> o = this.service.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Curso cursoDb = o.get();
        cursoDb.removeAlumno(alumnoRequest);
        Curso cursoFinal = service.save(cursoDb);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoFinal);
    }

    @Operation(description = "Return curso updated, examen asigned")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Curso updated"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @PutMapping("/{id}/asignar-examenes")
    public ResponseEntity<?> asignarExamenes(@Valid @RequestBody List<Examen> examenRequest,
                                             @PathVariable Long id){
        Optional<Curso> o = this.service.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Curso cursoDb = o.get();
        examenRequest.forEach(cursoDb::addExamen);
        Curso cursoFinal = service.save(cursoDb);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoFinal);
    }

    @Operation(description = "Return curso updated, examen deleted")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Curso updated"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @PutMapping("/{id}/eliminar-examen")
    public ResponseEntity<?> eliminarExamen(@Valid @RequestBody Examen examenRequest, BindingResult result,
                                            @PathVariable Long id){

        if(result.hasErrors()){
            return this.validar(result);
        }

        Optional<Curso> o = this.service.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Curso cursoDb = o.get();
        cursoDb.removeExamen(examenRequest);
        Curso cursoFinal = service.save(cursoDb);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoFinal);
    }

    private ResponseEntity<?> validar(BindingResult result){
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
