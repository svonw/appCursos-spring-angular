package com.udemyfullstack.microservicios.app.respuestas.services;

import com.udemyfullstack.microservicios.app.respuestas.models.entity.Respuesta;

public interface RespuestaService {
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);

	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);

	public Iterable<Long> findExamenesIdRespondidosByAlumno(Long alumnoId);
}
