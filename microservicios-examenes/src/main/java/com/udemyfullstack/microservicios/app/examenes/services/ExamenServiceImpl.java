package com.udemyfullstack.microservicios.app.examenes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udemyfullstack.microservicios.app.examenes.models.repository.AsignaturaRepository;
import com.udemyfullstack.microservicios.app.examenes.models.repository.ExamenRepository;
import com.udemyfullstack.microservicios.generic.examenes.models.entity.Asignatura;
import com.udemyfullstack.microservicios.generic.examenes.models.entity.Examen;
import com.udemyfullstack.microservicios.generic.service.GenericServiceImpl;

@Service
public class ExamenServiceImpl extends GenericServiceImpl<Examen, ExamenRepository> implements ExamenService {

	@Autowired
	private AsignaturaRepository asignaturaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombre(String text) {

		return repository.findByNombre(text);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Asignatura> findAllAsignaturas() {
		return (List<Asignatura>) asignaturaRepository.findAll();
	}

}
