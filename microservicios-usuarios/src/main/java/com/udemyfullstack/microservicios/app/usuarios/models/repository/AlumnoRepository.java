package com.udemyfullstack.microservicios.app.usuarios.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.udemyfullstack.microservicios.generic.alumnos.models.entity.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno, Long>, PagingAndSortingRepository<Alumno, Long> {

	@Query("select a from Alumno a where a.nombre like %?1% or a.apellido like %?1%")
	public List<Alumno> finByNombreOrApellido(String texto);
}
