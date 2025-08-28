package com.udemyfullstack.microservicios.app.examenes.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.udemyfullstack.microservicios.generic.examenes.models.entity.Examen;

public interface ExamenRepository extends CrudRepository<Examen, Long>, PagingAndSortingRepository<Examen, Long> {

	@Query("select e from Examen e where e.nombre like %?1%")
	public List<Examen> findByNombre(String text);

}
