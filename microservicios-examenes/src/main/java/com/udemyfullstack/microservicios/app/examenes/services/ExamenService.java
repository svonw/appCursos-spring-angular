package com.udemyfullstack.microservicios.app.examenes.services;

import java.util.List;

import com.udemyfullstack.microservicios.generic.examenes.models.entity.Asignatura;
import com.udemyfullstack.microservicios.generic.examenes.models.entity.Examen;
import com.udemyfullstack.microservicios.generic.service.GenericService;

public interface ExamenService extends GenericService<Examen> {
	public List<Examen> findByNombre(String text);

	public List<Asignatura> findAllAsignaturas();
}
