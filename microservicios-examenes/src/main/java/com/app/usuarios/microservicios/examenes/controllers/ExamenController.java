package com.app.usuarios.microservicios.examenes.controllers;

import com.app.usuarios.microservicios.commons.models.entity.Examen;
import com.app.usuarios.microservicios.examenes.services.ExamenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Tag(name = "Examen API", description = "This APi serve all functionality for management Examenes")
@RestController
public class ExamenController {

    @Autowired
    private ExamenService service;

    @Operation(description = "Return all page bundled into Response", summary ="Return 204 if no data found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Exito: Páginas."),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("/pagina")
    public ResponseEntity<?> paginas(Pageable pageable){
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @Operation(description = "Return all examenes bundled into Response", summary ="Return 204 if no data found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Exito: Listado de examenes."),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @Operation(description = "Return examen by id bundled into Response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Examen"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id){
        Optional<Examen> o = service.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(o.get());
    }

    @Operation(description = "Return curso updated bundled into Response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Curso"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Examen examen){
        Examen examenDB = service.save(examen);
        return ResponseEntity.status(HttpStatus.CREATED).body(examenDB);
    }

    @Operation(description = "Return examen updated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Curso updated"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Examen examen, @PathVariable Long id){

        Optional<Examen> o = service.findById(id);
        if(o.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Examen examenDB = o.get();
        examenDB.setNombre(examen.getNombre());

        examenDB.getPreguntas()
                .stream()
                .filter(pdb -> !examen.getPreguntas().contains(pdb))
                .forEach(examenDB::removePregunta);

        examenDB.setPreguntas(examen.getPreguntas());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDB));

    }

    @Operation(description = "Return code 204")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Examen deleted"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Return examen by filter bundled into Response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Examen"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("/filtrar/{value}")
    public ResponseEntity<?> filtrar(@PathVariable String value){
        Optional<List<Examen>> olist = service.findByNombre(value);
        if(olist.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(olist.get());
    }

    @Operation(description = "Return asignaturas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Asignaturas"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("/asignaturas")
    public ResponseEntity<?> listarAsignaturas(){
        return ResponseEntity.ok().body(service.findAllAsignaturas());
    }
}
