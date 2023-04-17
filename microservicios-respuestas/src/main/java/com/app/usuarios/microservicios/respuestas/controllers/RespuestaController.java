package com.app.usuarios.microservicios.respuestas.controllers;

import com.app.usuarios.microservicios.respuestas.models.entity.Respuesta;
import com.app.usuarios.microservicios.respuestas.services.RespuestaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RespuestaController {

    @Autowired
    private RespuestaService service;

    @Operation(description = "Return examenes updated bundled into Response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Examenes"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @PostMapping
    public ResponseEntity<?> crear (@RequestBody Iterable<Respuesta> respuestas){
        Iterable<Respuesta> respuestasDb = service.saveAll(respuestas);
        return  ResponseEntity.status(HttpStatus.CREATED).body(respuestasDb);
    }


    @Operation(description = "Return respuesta by Alumno id and Examen id bundled into Response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Respuestas"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("/alumno/{alumnoId}/examen/{examenId}")
    public ResponseEntity<?> obtenerRespuestasPorAlumnoPorExamen(@PathVariable Long alumnoId,
                                                                 @PathVariable Long examenId){
        Iterable<Respuesta> respuestas = service.findRespuestaByAlumnoByExamen(alumnoId, examenId);
        return ResponseEntity.ok(respuestas);

    }

    @Operation(description = "Return examnes respondidos by Alumno id bundled into Response")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Respuestas"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("/alumno/{alumnoId}/examenes-respondidos")
    public ResponseEntity<?> obtenerExamenesIdsConRespuestasAlumno(@PathVariable Long alumnoId){
        Iterable<Long> respuestas = service.findExamenesIdConRespuestasByAlumno(alumnoId);
        return ResponseEntity.ok(respuestas);

    }
}
