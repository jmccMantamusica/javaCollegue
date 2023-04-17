package com.app.usuarios.microservicios.respuestas.services;

import com.app.usuarios.microservicios.respuestas.models.entity.Respuesta;
import org.springframework.stereotype.Service;


public interface RespuestaService {

     public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);

    public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);

    public Iterable<Long> findExamenesIdConRespuestasByAlumno(Long alumnoId);
}
