package com.udemyfullstack.microservicios.app.respuestas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemyfullstack.microservicios.app.respuestas.models.entity.Respuesta;
import com.udemyfullstack.microservicios.app.respuestas.services.RespuestaService;

@RestController
@RequestMapping("/api/respuestas")
public class RespuestaController {

	@Autowired
	private RespuestaService service;

	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Iterable<Respuesta> respuestas) {
		Iterable<Respuesta> respuestasDb = service.saveAll(respuestas);
		return ResponseEntity.status(HttpStatus.CREATED).body(respuestasDb);
	}

	@GetMapping("/alumno/{alumnoId}/examen/{examenId}")
	public ResponseEntity<?> obtenerRespuestasPorAlumnoExamen(@PathVariable Long alumnoId,
			@PathVariable Long examenId) {
		Iterable<Respuesta> respuestas = service.findRespuestaByAlumnoByExamen(alumnoId, examenId);

		return ResponseEntity.ok(respuestas);
	}

	@GetMapping("/alumno/{alumnoId}/examenes-respondidos")
	public ResponseEntity<?> obtenerExamenesRespondidosByAlumno(@PathVariable Long alumnoId) {
		Iterable<Long> examenesId = service.findExamenesIdRespondidosByAlumno(alumnoId);
		return ResponseEntity.ok(examenesId);
	}

}
