package com.practice.app.usuarios.controllers;

import com.practice.app.usuarios.commons.ObjectMapper;
import com.practice.app.usuarios.models.dto.CursoRequest;
import com.practice.app.usuarios.models.dto.CursoResponse;
import com.practice.app.usuarios.models.entity.Curso;
import com.practice.app.usuarios.services.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Curso API", description = "This APi serve all functionality for management Cursos")
@RestController
public class CursoController {

    @Autowired
    private CursoService service;

    @Autowired
    private ObjectMapper<Curso, CursoRequest> request;

    @Autowired
    private ObjectMapper<Curso, CursoResponse> response;

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
        return ResponseEntity.ok(response.EntityToDto(o.get()));
    }

    @Operation(description = "Return curso updated bundled into Response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Curso"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CursoRequest cursoRequest){
        Curso curso = request.DtoToEntity(cursoRequest);
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

        return ResponseEntity.status(HttpStatus.CREATED).body(response.EntityToDto(curso));

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
}
