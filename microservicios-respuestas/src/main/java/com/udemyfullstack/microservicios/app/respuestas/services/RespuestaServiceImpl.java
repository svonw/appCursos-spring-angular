package com.udemyfullstack.microservicios.app.respuestas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udemyfullstack.microservicios.app.respuestas.models.entity.Respuesta;
import com.udemyfullstack.microservicios.app.respuestas.models.repository.RespuestaRepository;
import com.udemyfullstack.microservicios.generic.examenes.models.entity.Pregunta;

@Service

public class RespuestaServiceImpl implements RespuestaService {

	@Autowired
	private RespuestaRepository repository;

	@Override
	@Transactional
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
		
		respuestas.forEach(respuesta -> {
			if (respuesta.getPregunta() != null && respuesta.getPregunta().getId() != null) {
				Optional<Pregunta> preguntaConExamen = repository.findPreguntaWithExamen(respuesta.getPregunta().getId());
				if (preguntaConExamen.isPresent()) {
					respuesta.setPregunta(preguntaConExamen.get());
				}
			}
		});

		return repository.saveAll(respuestas);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {

		return repository.findRespuestaByAlumnoByExamen(alumnoId, examenId);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Long> findExamenesIdRespondidosByAlumno(Long alumnoId) {

		return repository.findExamenesIdRespondidosByAlumno(alumnoId);
	}

}
